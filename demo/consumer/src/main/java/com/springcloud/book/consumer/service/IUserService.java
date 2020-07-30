package com.springcloud.book.consumer.service;

import com.springcloud.book.consumer.model.User;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "provider")
public interface IUserService {

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username);

    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    String helloFeign();

    /**
     * feign请求结果是一个图片流，怎么接收123
     * 生成图片验证码
     *
     * @param imagekey
     * @return
     */
    @RequestMapping(value = "createImagesCode")
    Response createImageCode(@RequestParam("imagekey") String imagekey);

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    String updateUser(@RequestBody User user);

    /***
     * 1.produces,consumes必填
     * 2.注意区分@RequestPart和RequestParam，不要将
     * @RequestPart(value = "file") 写成@RequestParam(value = "file")
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadFile/server",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String fileUpload(@RequestPart(value = "file") MultipartFile file);
}
