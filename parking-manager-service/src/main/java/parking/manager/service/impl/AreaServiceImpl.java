package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.manager.mapper.AreaMapper;
import parking.manager.service.IAreaService;

/**
 * @author huang
 * @date 2020/1/13 15:46
 * @Disc
 **/
@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private AreaMapper mapper;
}
