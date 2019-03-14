package com.yhb.www.dbDao;

import com.yhb.www.entity.Result;
import com.yhb.www.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 好友关系处理类
 */
@Controller
@RequestMapping(value = "/friendDao")
public class FriendDaoImpl extends BaseDao implements FriendDao{

    private static final String insert = "insert into friend(userId,friendId,isAdd)values(?,?,?)";
    private static final String delect = "delete from friend where isAdd = ? and " +
            "((userId = ? and friendId = ?) or (userId = ? and friendId = ?))";
    private static final String update = "update friend set isAdd = 1 where " +
            "((userId = ? and friendId = ?) or (userId = ? and friendId = ?)) limit 1";//确认添加好友同意
    private static final String isCheck = "select friendId, userId, isAdd from friend where " +
            "((userId = ? and friendId = ?) or (userId = ? and friendId = ?)) and isAdd = ?";
    private static final String selectAll = "select b.uId,b.token, b.name, b.phone, b.hread from friend a , users b where a.isAdd = ? and " +
            "((a.userId = ? and a.friendId = b.uId) or (a.friendId = ? and a.userId = b.uId))";
    private static final String selectLike = "select b.uId, b.name, b.phone, b.hread from friend a , users b where a.isAdd = 1 and " +
            "((a.userId = ? and a.friendId = b.uId) or (a.friendId = ? and a.userId = b.uId)) " +
            "and b.phone like ? and b.name like ?";

    //请求添加好友
    @RequestMapping(value = "/addFriend" )
    @ResponseBody
    @Override
    public Result addFriend(@RequestParam(value = "userId") String userId,
                            @RequestParam(value = "friendId") String friendId) {
        if (isCheckFriend(userId,friendId,1)){
            return new Result(0,"好友已存在，不允许重复添加");
        }
       else if(userId.equals(friendId)){
            return new Result(0,"不允许添加自己");
        }else{
            List<Object> params = new ArrayList<>();
            params.add(userId);
            params.add(friendId);
            params.add(0);
            if(this.executeUpdate(insert, params)>0)
                return new Result(1,"已发送请求!");
            else return new Result(0,"请求添加好友失败");
        }
    }

    //删除好友关系
    @RequestMapping(value = "/delectFriendByUid" )//, method = RequestMethod.GET)
    @ResponseBody
    @Override
    public Result delectFriendByUid(@RequestParam(value = "userId")String userId,
                                    @RequestParam(value = "friendId")String friendId) {
        if(userId == null || userId.equals("") || friendId==null || friendId.equals(""))
            return new Result(0,"不可输入空值");
        List<Object> params = new ArrayList<>();
        params.add(1);params.add(userId);params.add(friendId);
        params.add(friendId);params.add(userId);
        if(this.executeUpdate(delect,params)>0){
            return new Result(1,"已删除该好友");
        }
        else{
            return new Result(0,"删除失败");
        }
    }

    //通过用户名或者手机号模糊查找好友
    @RequestMapping(value = "/selectFriendLikePhoneAndName" , method = RequestMethod.GET)
    @ResponseBody
    @Override
    public Result selectFriendLikePhoneAndName(@RequestParam(value = "userId")String userId,
                                               @RequestParam(value = "phone")String phone,
                                               @RequestParam(value = "name")String name){
        if(userId == null || userId.equals("")){
            return new Result(0,"不可输入空值进行查询");
        }
        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(userId);
        params.add("%"+phone+"%");
        params.add("%"+name+"%");
        ResultSet rs = this.executeQuery(selectLike, params);
        List<User> result = new ArrayList<>();
        try {
            while(rs.next()){
                User user = new User();
                user.setuId(rs.getString("uId"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setHread(rs.getString("hread"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //同意添加该好友
    @RequestMapping(value = "/updateFriendByUid")
    @ResponseBody
    @Override
    public Result updateFriendByUid(@RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "friendId") String friendId) {
        //除了更新一条好友请求同意外还要删除这对好友关系的所有的好友请求
        List<Object> params = new ArrayList<>();
        if(userId == null || userId.equals("") || friendId==null || friendId.equals(""))
            return new Result(0,"不可输入空值");
        params.add(userId);params.add(friendId);
        params.add(friendId);params.add(userId);
        int i = this.executeUpdate(update, params);
        //清空这对好友的添加请求
        params.clear();params.add(0);params.add(userId);
        params.add(friendId);params.add(friendId);params.add(userId);
        this.executeUpdate(delect,params);
        if(i>0){
            return new Result(1,"添加好友成功！");
        }
        else{
            return new Result(0,"添加好友失败！");
        }
    }

    //查询我的已加好友或好友请求isAdd 0 未确认的好友请求 1已添加的好友
    @RequestMapping(value = "/selectFriendOrAgree")
    @ResponseBody
    @Override
    public Result selectFriendOrAgree(@RequestParam(value = "userId") String userId,
                                      @RequestParam(value = "isAdd") int isAdd) {
        List<Object> params = new ArrayList<>();
        if(userId == null || userId.equals("")){
            return new Result(0,"不可输入空值");
        }else{
            params.add(isAdd);
            params.add(userId);
            params.add(userId);
        }
        ResultSet rs = this.executeQuery(selectAll, params);
        List<User> result = new ArrayList<>();
        try {
            while(rs.next()){
                User user = new User();
                user.setuId(rs.getString("uId"));
                user.setToken(rs.getString("token"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setHread(rs.getString("hread"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //判断是否存在好友关系
    @RequestMapping(value = "/isCheckFriend")// , method = RequestMethod.GET
    @ResponseBody
    @Override
    public boolean isCheckFriend(@RequestParam(value = "userId") String userId,
                                 @RequestParam(value = "friendId")String friendId,
                                @RequestParam(value = "isAdd") int isAdd) {
        List<Object> params = new ArrayList<>();
        params.add(userId);
        params.add(friendId);
        params.add(friendId);
        params.add(userId);
        params.add(isAdd);
        ResultSet rs = this.executeQuery(isCheck, params);
        try {
            if(rs.next())return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return false;
    }

}
