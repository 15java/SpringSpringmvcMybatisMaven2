package Maven.crud.bb;


import Maven.crud.aa.Addr;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建人:冯嘉裕
 * 创建时间:2022/12/2 10:30
 **/
@Repository
public interface Addrs {

    //新增
    public List<Addr> getAddr();


    //根据一个ID查询
    public Addr getById(int id);


}