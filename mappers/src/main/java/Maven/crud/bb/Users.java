package Maven.crud.bb;


import Maven.crud.aa.Page;
import Maven.crud.aa.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建人:冯嘉裕
 * 创建时间:2022/12/2 10:30
 **/
@Repository
public interface Users {

    //查询所有
    public List<User> getAll(Page page);

    //新增
    public void addUser(User user);

    //查询总数据
    public Integer getCount(Page page);

    //删除
    public void delete(int id);

    //根据一个ID查询
    public User getUserById(int id);

    //修改
    public void update(User user);

}
