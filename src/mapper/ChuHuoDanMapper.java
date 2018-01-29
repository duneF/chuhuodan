package mapper;

import bean.ChuHuoDan;

import java.util.HashMap;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:16 2018/1/10
 * @Modified By:
 */
public interface ChuHuoDanMapper {
    void add(ChuHuoDan dan);

    List<ChuHuoDan> findAll(HashMap map);

    void delById(Integer id);
    ChuHuoDan findChuHuoDanById(Integer id);
    void updateItemById(ChuHuoDan chuHuoDan);

    Integer findCount();

    List<ChuHuoDan> findAllByCountName(HashMap map);
}
