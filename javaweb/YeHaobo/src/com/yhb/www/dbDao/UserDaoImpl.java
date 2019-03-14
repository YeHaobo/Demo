package com.yhb.www.dbDao;

import com.yhb.www.entity.User;
import com.yhb.www.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yhb.www.entity.Result;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息表编辑类
 */
@Controller
@RequestMapping(value = "/userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

    private static final String insert = "insert into users(uId,time,token,phone,name,pwd,hread)values(?,?,?,?,?,?,?)";
    private static final String findAllUser = "select uId,time,token,phone,name,pwd,hread from users";
    private static final String findUsersById = "select uId,time,token,phone,name,pwd,hread from users where uId =?";
    private static final String findUsersByToken = "select uId,time,token,phone,name,pwd,hread from users where token =?";
    private static final String findUserByPhoneAndName = "select uId,time,token,phone,name,pwd,hread from users where";
    private static final String deleteByUid = "delete from users where uId = ?";
    private static final String updateUserByUid = "update users set name = ? , pwd = ? , hread = ? where uId = ?";
    private static final String signIn = "select uId,time,token,phone,name,pwd,hread from users where phone = ? and pwd = ?";
    //@Autowired//该注解为注入外部类或方法
    //添加用户
    @RequestMapping(value = "/addUser")
    @ResponseBody
    @Override
    public Result addUser(@RequestParam(value = "phone")String phone , @RequestParam(value = "name")String name,
                          @RequestParam(value = "pwd")String pwd, @RequestParam(value = "hread")String hread) {
        if(isCheckUserByPhone(phone)){
            return new Result(0,"当前用户已注册");
        }
        List<Object> params = new ArrayList<>();
        String[] uuids=Utils.getUUID(1);
        params.add(uuids[0]);
        params.add(Utils.getMyTime());
        params.add(phone+uuids[0]);
        params.add(phone);
        params.add(name);
        params.add(pwd);
        params.add(hread);
        if(this.executeUpdate(insert, params)>0)
            return new Result(1,"用户"+phone+"成功注册！");
        else return new Result(0,"注册失败");
    }

    //查找所有用户
    @RequestMapping(value = "/findAllUser")
    @ResponseBody
    @Override
    public Result findUsers() {
        List<User> result = new ArrayList<>();
        ResultSet rs = this.executeQuery(findAllUser, null);
        try {
            while(rs.next()){
                String uId = rs.getString("uId");
                Timestamp time = rs.getTimestamp("time");
                String token = rs.getString("token");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String pwd =rs.getString("pwd");
                String hread =rs.getString("hread");
                User user = new User(uId,time, token,phone,name,pwd,hread);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //根据Id查找用户
    @RequestMapping(value = "/findUsersById")
    @ResponseBody
    @Override
    public Result findUsersById(@RequestParam(value = "uId")String uId) {
        List<Object> params = new ArrayList<>();
        if(uId == null || uId.equals("")){
            return new Result(0,"不可输入空值");
        }else{
            params.add(uId);
        }
        List<User> result = new ArrayList<>();
        ResultSet rs = this.executeQuery(findUsersById, params);
        try {
            while(rs.next()){
                String uId1 = rs.getString("uId");
                Timestamp time = rs.getTimestamp("time");
                String token = rs.getString("token");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String pwd =rs.getString("pwd");
                String hread =rs.getString("hread");
                User user = new User(uId1,time, token,phone,name,pwd,hread);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //根据token查找用户
    @RequestMapping(value = "/findUsersByToken")
    @ResponseBody
    @Override
    public Result findUsersByToken(@RequestParam(value = "token")String token) {
        List<Object> params = new ArrayList<>();
        if(token == null || token.equals("")){
            return new Result(0,"不可输入空值");
        }else{
            params.add(token);
        }
        List<User> result = new ArrayList<>();
        ResultSet rs = this.executeQuery(findUsersByToken, params);
        try {
            while(rs.next()){
                String uId1 = rs.getString("uId");
                Timestamp time = rs.getTimestamp("time");
                String token1 = rs.getString("token");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String pwd =rs.getString("pwd");
                String hread =rs.getString("hread");
                User user = new User(uId1,time, token1,phone,name,pwd,hread);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //根据用户手机号或用户名查找相似
    @RequestMapping(value = "/findUserLikePhoneAndName")
    @ResponseBody
    @Override
    public Result findUserLikePhoneAndName(@RequestParam(value = "phone")String phone,
                                           @RequestParam(value = "name")String name) {
        List<Object> params = new ArrayList<>();
        String sql  = findUserByPhoneAndName+" phone like ? and name like ?";
        params.add("%"+phone+"%");
        params.add("%"+name+"%");
        ResultSet rs = this.executeQuery(sql, params);
        List<User> result = new ArrayList<>();
        try {
            while(rs.next()){
                String uId1 = rs.getString("uId");
                Timestamp time = rs.getTimestamp("time");
                String token = rs.getString("token");
                String phone1 = rs.getString("phone");
                String name1 = rs.getString("name");
                String pwd =rs.getString("pwd");
                String hread =rs.getString("hread");
                User user = new User(uId1,time, token,phone1,name1,pwd,hread);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return new Result(1,result);
    }

    //通过Id删除用户信息
    @RequestMapping(value = "/delectUserByUid")
    @ResponseBody
    @Override
    public Result delectUserByUid(@RequestParam(value = "uId")String uId) {
        if(uId == null || uId.equals("")){
            return new Result(0,"不可输入空值");
        }
        List<Object> params = new ArrayList<>();
        params.add(uId);
        if(this.executeUpdate(deleteByUid, params)<=0){
            return new Result(0,"删除失败");
        }else{
            return new Result(1,"删除成功");
        }

    }

    //通过ID 更改用户信息
    @RequestMapping(value = "/updateUserByUid")
    @ResponseBody
    @Override
    public Result updateUserByUid(@RequestParam(value = "uId")String uId ,@RequestParam(value = "name")String name,
                                  @RequestParam(value = "pwd")String pwd,@RequestParam(value = "hread")String hread) {
        if(!isCheckUserById(uId)){
            return new Result(0,"不存在该用户，更改信息失败");
        }
        List<Object> params = new ArrayList<>();
        params.add(name);
        params.add(pwd);
        params.add(hread);
        params.add(uId);
        if(this.executeUpdate(updateUserByUid, params)<=0){
            return new Result(0 , "更改失败");
        }
        else{
            return new Result(1 , "更改成功");
        }
    }

    //判断手机号是否存在
    @Override
    public boolean isCheckUserByPhone(String phone) {
        String sql=findUserByPhoneAndName+" phone = ?";
        List<Object> params = new ArrayList<>();
        params.add(phone);
        ResultSet rs = this.executeQuery(sql, params);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return false;
    }

    //判断uId是否存在
    @Override
    public boolean isCheckUserById(String uId){
        String sql=findUserByPhoneAndName+" uId = ?";
        List<Object> params = new ArrayList<>();
        params.add(uId);
        ResultSet rs = this.executeQuery(sql, params);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        return false;
    }

    //用户登录
    //用户登录接口
    @RequestMapping(value = "/signIn")
    @ResponseBody
    public Result signIn(@RequestParam(value = "phone")String phone, @RequestParam(value = "pwd") String pwd){
        List<Object> params = new ArrayList<>();
        if(phone == null || phone.equals("") || pwd == null||pwd.equals("")){
            return new Result(0,"不可输入空值!");
        }
        params.add(phone);
        params.add(pwd);
        List<User> result = new ArrayList<>();
        ResultSet rs = this.executeQuery(signIn, params);
        try {
            while(rs.next()){
                String uId = rs.getString("uId");
                Timestamp time = rs.getTimestamp("time");
                String token = rs.getString("token");
                String phone1 = rs.getString("phone");
                String name = rs.getString("name");
                String pwd1 =rs.getString("pwd");
                String hread =rs.getString("hread");
                User user = new User(uId,time, token,phone1,name,pwd1,hread);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.close();
        }
        if(result.size() == 1){
            return new Result(1,result);
        }else{
            return new Result(0,"用户名或密码不正确！");
        }
    }

}
