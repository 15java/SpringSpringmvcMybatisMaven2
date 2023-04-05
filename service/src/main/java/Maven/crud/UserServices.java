package Maven.crud;


import Maven.crud.aa.Addr;
import Maven.crud.aa.Page;
import Maven.crud.aa.User;
import Maven.crud.bb.Addrs;
import Maven.crud.bb.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author:冯嘉裕
 * Date2022/12/7 17:01
 **/
@Service
public class UserServices {
    @Autowired
    private Users users;

    @Autowired
    private Addrs addrs;


    //分页查询
    @Transactional
    public void Count(Page<User> page) {
        //查询总页数的数据
        int count = users.getCount(page);
        //int a =(page.get当前页数（）-1* page.get每页多少条数据（）);
        int p = (page.getPageNo() - 1) * page.getPageSize();//每页多少条数据          //当前第几页
        page.setP(p);
        page.setSum(count);
        page.setList(users.getAll(page));
    }

    //查询部门
    @Transactional
    public List<Addr> getAddr() {
        return addrs.getAddr();
    }

    //user查询一个id
    @Transactional
    public User chaxunid(int id) {

        return users.getUserById(id);
    }

    //新增数据
    @Transactional
    public void Useradd(User user) {
        users.addUser(user);

    }

    //删除
    @Transactional
    public void delete(int id) {
        users.delete(id);

    }

    //修改
    @Transactional
    public void update(User user) {
        users.update(user);
    }

}
