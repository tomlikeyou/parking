package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import parking.common.AjaxResult;
import parking.common.AjaxResultBuilder;
import parking.common.CarType;
import parking.common.ResultCode;
import parking.manager.service.ICarTypeService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Author: huang
 * Date: created in 2020/3/25 22:17
 * Description:
 *
 * @author 24626
 */
@RestController
public class CarTypeController {

    @Autowired
    private ICarTypeService carTypeService;

    @GetMapping("/carTypeWithCars")
    public Object findTypeWithCars(){
        List<CarType> carTypes = carTypeService.findCarTypeWithCars();
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS,ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS),carTypes);
    }

    @GetMapping("/carTypes")
    public Object findCarTypes() {
        List<CarType> list = carTypeService.findCarTypes();
        return (list != null && list.size() > 0) ?
                AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), list) :
                AjaxResultBuilder.build(ResultCode.SELECT_FAIL, ResultCode.findMessageByCode(ResultCode.SELECT_FAIL), null);
    }

    @PostMapping("/carFile")
    public Object uploadImage(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResultBuilder.build(ResultCode.SELECT_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
        } else {
            //图片保存路径
            String realPath = "";
            try {
                realPath = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //文件名称
            String filename = file.getOriginalFilename();
            //拿到文件后缀名
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //新的文件名
            String newName = UUID.randomUUID() + suffixName;
            File actualFile = new File(realPath + "static/images/" + newName);
            if (!actualFile.getParentFile().exists()) {
                actualFile.mkdirs();
            }
            try {
                file.transferTo(actualFile);
                CarType carType = new CarType();
                Random random = new Random();
                carType.setTypeId(random.nextInt(Integer.MAX_VALUE - 1));
                carType.setCarTypeImage(newName);
                int flag = carTypeService.save(carType);
                return flag > 0 ?
                        AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), null)
                        : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
