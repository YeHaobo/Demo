package data.mysql.com.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;
import data.mysql.com.greendaotest.entity.User;
import data.mysql.com.greendaotest.utils.Time;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText idEt,nameEt,phoneEt,sexEt,ageEt;
    private MessageAdapter msgAdapter,resultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();//数据库创建
        initView();
    }
    //控件初始化
    private void initView(){
        Button addBtn = findViewById(R.id.add_user); addBtn.setOnClickListener(this);
        Button updataBtn = findViewById(R.id.updata_user); updataBtn.setOnClickListener(this);
        Button deleteBtn = findViewById(R.id.delect_user); deleteBtn.setOnClickListener(this);
        Button selectBtn = findViewById(R.id.select_user); selectBtn.setOnClickListener(this);
        idEt = findViewById(R.id.user_id);
        nameEt = findViewById(R.id.user_name);
        phoneEt = findViewById(R.id.user_phone);
        sexEt = findViewById(R.id.user_sex);
        ageEt = findViewById(R.id.user_age);
        RecyclerView userRecycler = findViewById(R.id.user_recycler);
        userRecycler.setNestedScrollingEnabled(true);
        userRecycler.setLayoutManager( new GridLayoutManager(this,1));
        msgAdapter = new MessageAdapter(this,new ArrayList<User>());
        userRecycler.setAdapter(msgAdapter);
        RecyclerView resultRecycler = findViewById(R.id.result_message);
        resultRecycler.setNestedScrollingEnabled(true);
        resultRecycler.setLayoutManager( new GridLayoutManager(this,1));
        resultAdapter = new MessageAdapter(this,new ArrayList<User>());
        resultRecycler.setAdapter(resultAdapter);
    }

    /**
     * 添加一行数据
     */
    public void addUserToSQL(String id,String name,String phone,String sex,int age){
        User user = new User(id,name,phone,sex,age,Time.getLocaleTime());
        if(user.save()){
            Toast.makeText(this,"保存信息成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"保存信息失败",Toast.LENGTH_SHORT).show(); 
        }
    }
    /**
     * 更新数据(判断ID与性别相同时更改表中相应字段的年龄)
     */
    public void upDateToSQL(int age,String sex,String name){
        User user = new User();
        user.setUser_age(age);
        int index = user.updateAll("user_sex = ? and user_name = ?",sex,name);
        if(index > 0){
            Toast.makeText(this,"更新信息成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"更新信息失败",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 删除数据（判断ID与性别删除表中相应字段）
     */
    public void deleteToSQL(String sex,String name){
        int index;
        index = DataSupport.deleteAll(User.class,"user_sex = ? and user_name = ?",sex,name);
        if(index > 0){
            Toast.makeText(this,"删除信息成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"删除信息失败",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *查询所有信息
     */
    public List<User> selectAllToSQL(){
        //查询所有数据
        List<User> list = DataSupport.findAll(User.class);
        if(list == null)return new ArrayList<>();
        else return list;
    }

    /**
     * 使用语句进行查询
     */
    public List<User> selectBySQL(String sex,String name){
        List<User> list = DataSupport.where("user_sex = ? and user_name = ?",sex,name).find(User.class);
        if(list == null)return new ArrayList<>();
        else return list;
        /**
         * 可以使用语句进行查询
         * String sql = "select * from user where sex = ? and name = ?";
         * Cursor cursor = DataSupport.findBySQL(sql,sex,name);
         *  但是因为返回的是游标需要使用游标进行原生解析
         */
        /**
         * //select()  查询制定的列
         *         List<User> list01 = DataSupport.select("name","age").find(User.class);
         *         //where() 根据约束条件查询
         *         List<User> list02 = DataSupport.where("age > ?","20").find(User.class);
         *         //order() 对查询的结果进行排序 desc 表示降序排列 asc或者不写表示升序排列
         *         List<User> list03 = DataSupport.order("student_number desc").find(User.class);
         *         //limit() 指定查询结果的数量
         *         List<User> list04 = DataSupport.limit(3).find(User.class);
         *         //offset() 制定查询结果的偏移量 比如查询地2,3 条数据
         *         List<User> list05 = DataSupport.offset(1).limit(2).find(User.class);
         *         //以上的所有方法可以任意搭配组合，来完成复杂的查询操作
         *         List<User> list06 = DataSupport.select("name","age","student_number")
         *                 .where("age >?","20")
         *                 .order("student_number")
         *                 .limit(5)
         *                 .find(Student.class);
         */
    }

    @Override
    public void onClick(View v) {
        String id = idEt.getText().toString().trim()+"";
        String name = nameEt.getText().toString().trim()+"";
        String phone = phoneEt.getText().toString().trim()+"";
        String sex = sexEt.getText().toString().trim()+"";
        int age = 0;
        if(!(ageEt.getText().toString()+"").equals(""))age = Integer.parseInt(ageEt.getText().toString());
        switch (v.getId()){
            case R.id.add_user:
                if(id.equals("") || name.equals("") || phone.equals("") || sex.equals("") || age == 0){
                    Toast.makeText(this,"信息未输入完整",Toast.LENGTH_SHORT).show();
                }else{
                    addUserToSQL(id,name,phone,sex,age);
                }
                break;
            case R.id.updata_user:
                if(name.equals("") || sex.equals("") || age == 0){
                    Toast.makeText(this,"根据姓名性别更新年龄",Toast.LENGTH_SHORT).show();
                }else{
                    upDateToSQL(age,sex,name);
                }
                break;
            case R.id.select_user:
                if(name.equals("") || sex.equals("")){
                    Toast.makeText(this,"根据姓名性别查询",Toast.LENGTH_SHORT).show();
                }else{
                    resultAdapter.userList = selectBySQL(sex,name);
                    resultAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.delect_user:
                if(name.equals("") || sex.equals("")){
                    Toast.makeText(this,"根据姓名性别进行删除",Toast.LENGTH_SHORT).show();
                }else{
                    deleteToSQL(sex,name);
                }break;
        }
        msgAdapter.userList = selectAllToSQL();
        msgAdapter.notifyDataSetChanged();
    }
}
