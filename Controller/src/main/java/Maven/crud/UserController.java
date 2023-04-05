package Maven.crud;

import Maven.crud.aa.Addr;
import Maven.crud.aa.Page;
import Maven.crud.aa.User;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;

/**
 * Author:冯嘉裕
 * Date2022/12/7 16:58
 **/
@Controller
public class UserController {
    //(required = false)
    @Autowired
    private UserServices userService;
    @Autowired
    private FastFileStorageClient fastFileStorageClient; //注入操作fastdfs的接口
    //查询所有
    @RequestMapping("/query")
    public String getAll(Map<String, Object> map, Locale locale, Integer pageNo, Integer pageSize) {
        Page page = new Page();

        //pageNo
        if (pageNo == null || pageNo == 0) {
            page.setPageNo(1);
        } else {
            page.setPageNo(pageNo);
        }

        if (pageSize == null || pageSize == 0) {
            page.setPageSize(5);
        } else {
            page.setPageSize(pageSize);
        }
        userService.Count(page);

        map.put("locale", locale.toString());
        map.put("page", page);
        System.out.println("查询到：" + map);

        return "Query";
    }
    //新增设置值
    @RequestMapping(path = "/GetAdd", method = RequestMethod.GET)
    public String add(Map<String, Object> map, User user) {

        Map<String, String> sex = new HashMap<>();//把这个值xingbie放到request里面
        sex.put("1", "男");
        sex.put("0", "女");

        map.put("sex", sex);//也就是request放到这里来了 就是把xingbie放到request里面来

        List<Addr> list = userService.getAddr();

        map.put("addr", list);

        map.put("sex", sex);
        return "Add";
    }

    //新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid User user, BindingResult bindingResult, Map<String, Object> map, @RequestParam("head") MultipartFile head) throws IOException {

        if (bindingResult.getErrorCount() > 0) {
            System.out.println(user.toString());
            System.out.println("出错了!");

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            Map<String, String> sex = new HashMap<>();//把这个值xingbie放到request里面
            sex.put("1", "男");
            sex.put("0", "女");
            map.put("sex", sex);//也就是request放到这里来了 就是把jdk放到request里面来
            //把所有部门查出来放到request来
/*
            try {

                // 初始化配置文件
                ClientGlobal.init(conf_filename);
                // 创建跟踪器客户端对象
                TrackerClient tracker = new TrackerClient();
                // 获取跟踪器连接
                TrackerServer trackerServer = tracker.getTrackerServer();
                // 获取存储器客户端对象


                InputStream inputstream = head.getInputStream();
                byte[] b = new byte[inputstream.available()];

                inputstream.read(b);

                StorageClient storageClient = new StorageClient(trackerServer, null);

                String strs[] = storageClient.upload_file(b, null, null);


                user.setGroup(strs[0]);// group1
                user.setPath(strs[1]);// 0/00/00//xxxxxx.jpg

            } catch (Exception e) {
                e.printStackTrace();
            }
*/
            map.put("addr", userService.getAddr());
            map.put("user", user);

            return "Add";
        }

        try {
            String fiel = head.getOriginalFilename();//获取文件
            String[] fenge = fiel.split("\\.");//根据我的文件名来用\\.分割
            StorePath storePath = fastFileStorageClient.uploadFile(null, head.getInputStream(), head.getSize(), fenge[fenge.length - 1]);

            user.setGroup(storePath.getGroup());//获取到组
            user.setPath(storePath.getPath());//获 取到路径


            }catch (Exception e){
                e.printStackTrace();
            }

        userService.Useradd(user);
        System.out.println("你已新增头像" + "      " + user);
        System.out.println("-------------------------------------------");
        return "redirect:query";
    }

    //delete
    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        User user = userService.chaxunid(id);//获取到id

        String path = user.getPath();//获取到我的头像
        if (path != null && !path.equals("")) { //路径不等于null 或者不等于空就能进来
            File file = new File(path);
            file.delete();
        }

        try {
        fastFileStorageClient.deleteFile(user.getGroup(),user.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        userService.delete(id);
        System.out.println("你已删除头像" + "      " + user);
        return "redirect:/query";
    }

    //huixian
    @RequestMapping(value = "/Update/{id}", method = RequestMethod.GET)
    public String update(Map<String, Object> map, @PathVariable("id") Integer id) {
        //这个map是request
        Map<String, String> sex = new HashMap<String, String>();//把这个值xingbie放到request里面
        sex.put("1", "男");
        sex.put("0", "女");
        map.put("sex", sex);//也就是request放到这里来了 就是把jdk放到request里面来

        map.put("addr", userService.getAddr());
        map.put("user", userService.chaxunid(id));
        return "Add";
    }

    //update
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String xiu(@Valid User user, BindingResult bindingResult, Map<String, Object> map, @RequestParam("head") MultipartFile head) {
        if (bindingResult.getErrorCount() > 0) {
            System.out.println(user.toString());
            System.out.println("出错了!");

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            Map<String, String> sex = new HashMap<>();//把这个值xingbie放到request里面
            sex.put("1", "男");
            sex.put("0", "女");
            map.put("sex", sex);//也就是request放到这里来了 就是把jdk放到request里面来

            map.put("user", user);
            map.put("addr", userService.getAddr());
            return "Add";
        }



        fastFileStorageClient.deleteFile(user.getGroup(),user.getPath());//把组跟图片路径删了

        try {
            String fiel = head.getOriginalFilename();//获取文件
            String[] fenge = fiel.split("\\.");//根据我的文件名来用\\.分割
            StorePath storePath = fastFileStorageClient.uploadFile(null, head.getInputStream(), head.getSize(), fenge[fenge.length - 1]);
            user.setPath(storePath.getPath());//获取到路径
            user.setGroup(storePath.getGroup());//获取到组

        }catch (Exception e){
            e.printStackTrace();
        }

         userService.update(user);//把存值进去
        System.out.println("你已修改头像" + "      " + user);
        return "redirect:/query";
    }
    //每次进来都会执行
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {

        if (id != null) {
            User user = userService.chaxunid(id);
            map.put("user", user);
        }
    }

    //显示头像
    @RequestMapping("/path")
    public void test(HttpServletResponse response, String head, String group) {
/*        if (head != null || !head.equals(""))//head不等于null 或者不等于空 就可以进来
        {
                // 初始化配置文件
                ClientGlobal.init(conf_filename);
                // 创建跟踪器客户端对象
                TrackerClient tracker = new TrackerClient();
                // 获取跟踪器连接
                TrackerServer trackerServer = tracker.getTrackerServer();
                // 获取存储器客户端对象


                StorageClient storageClient = new StorageClient(trackerServer, null);
                byte[] b = storageClient.download_file(group, head);
                response.getOutputStream().write(b, 0, b.length);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        if(head != null || !head.equals(""))
        {
            try {
                byte[] bytes = fastFileStorageClient.downloadFile(group,head,new DownloadByteArray());//先组在路径
                response.getOutputStream().write(bytes, 0, bytes.length);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
/*      String[] fileName = head.split("\\\\");
            //addHeader和setHeader效果一样
            response.addHeader("Content-DIsposition", "attachment;fileName=" + fileName[fileName.length - 1]);
            InputStream inputStream = new FileInputStream(head);

            byte[] bytes = new byte[1024 * 1024 * 10];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                response.getOutputStream().write(bytes, 0, length);
            }
            inputStream.close();*/
        }

    }



