package service;

import bean.ChuHuoDan;
import mapper.ChuHuoDanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:15 2018/1/10
 * @Modified By:
 */
@Service
public class ChuHuoDanServiceImpl implements ChuHuoDanService{
    @Autowired
    private ChuHuoDanMapper chuHuoDanMapper;
    private HashMap<Object, Object> map;

    @Override
    public void add(ChuHuoDan dan) {
        Integer id=(int)((Math.random ()*9+1)*100000);
        dan.setId ( id );
        System.out.println (dan.toString ());
        chuHuoDanMapper.add(dan);
    }

    @Override
    public Integer findCount() {
        return chuHuoDanMapper.findCount();
    }

    @Override
    public List<ChuHuoDan> findAll(Integer pageNum,Integer pageSize){
        map = new HashMap<> ();
        map.put ( "pageNum",pageNum );
        map.put ( "pageSize",pageSize );
        return chuHuoDanMapper.findAll(map);
    }

    @Override
    public List<ChuHuoDan> findAllByCountName(String moHuName, Integer pageNum, Integer pageSize) {
       //将moHuName格式提前拼接等待在sql中实用
    	moHuName="'%"+moHuName+"%'";
        map.put ( "moHuName",moHuName );
        map.put ( "pageNum" ,pageNum);
        map.put ( "pageSize",pageSize );
        return chuHuoDanMapper.findAllByCountName(map);
    }
    @Override
    public void delById(Integer id) {
        chuHuoDanMapper.delById(id);
    }

    @Override
    public ChuHuoDan findChuHuoDanById(Integer id) {
        return    chuHuoDanMapper.findChuHuoDanById ( id );
    }

    @Override
    public void updateItemById(ChuHuoDan chuHuoDan) {
        chuHuoDanMapper.updateItemById ( chuHuoDan );
    }


}
