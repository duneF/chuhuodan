package service;

import bean.ChuHuoDan;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:09 2018/1/10
 * @Modified By:
 */
public interface ChuHuoDanService {
    void add(ChuHuoDan dan);

    List<ChuHuoDan> findAll(Integer pageNum,Integer pageSize);

    void delById(Integer id);
    ChuHuoDan findChuHuoDanById(Integer id);
    void updateItemById(ChuHuoDan chuHuoDan);

    Integer findCount();

    List<ChuHuoDan> findAllByCountName(String moHuName, Integer pageNum, Integer pageSize);

}
