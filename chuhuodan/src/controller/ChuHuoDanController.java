package controller;

import bean.ChuHuoDan;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ChuHuoDanService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:08 2018/1/10
 * @Modified By:
 */

@Controller
public class ChuHuoDanController extends Thread {
	@Autowired
	private ChuHuoDanService chuHuoDanService;
	private List<ChuHuoDan> allChuHuoDan;
	private List<ChuHuoDan> allCH;
	private ChuHuoDan chuHuoDanById;
	private Integer count;
	private Integer limitPage;
	private File xlsFile;
	private WritableWorkbook workbook;
	@RequestMapping("findAll")
	public String findAll(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
		if (pageNum == 0) {
			pageNum = 1;
		}
		allChuHuoDan = chuHuoDanService.findAll((pageNum - 1) * pageSize, pageSize);
		count = chuHuoDanService.findCount();
		limitPage = (int) Math.ceil(count / pageSize);
		// 存入model，发送到jsp
		model.addAttribute("list", allChuHuoDan);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("firstPage", 1);
		model.addAttribute("lastPage", limitPage);
		return "show.jsp";
	}

	@RequestMapping("addChuHuoDanToMySql")
	public String addHuoDan(ChuHuoDan dan) {
		System.out.println("controller");
		chuHuoDanService.add(dan);
		return "redirect:/findAll.do";
	}

	@RequestMapping("delItemById")
	public String delChuHuoDan(Integer id) {
		chuHuoDanService.delById(id);
		return "redirect:/findAll.do";
	}

	@RequestMapping("updateFindById")
	public String updateItemById(Integer id, Model model) {
		chuHuoDanById = chuHuoDanService.findChuHuoDanById(id);
		model.addAttribute("dan", chuHuoDanById);
		return "updata.jsp";
	}

	@RequestMapping("updateChuHuoDanById")
	public String updateChuHuoDanById(ChuHuoDan chuHuoDan) {
		chuHuoDanService.updateItemById(chuHuoDan);
		return "redirect:/findAll.do";
	}

	@RequestMapping("findChuHuoDanByCountName")
    public String findChuHuoDanByCountName(Model model, String moHuName,
                                           @RequestParam(value = "pageNum" ,defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize",defaultValue ="10000")Integer pageSize){
        if (pageNum==0){
            pageNum=1;
        }
        count = chuHuoDanService.findCount ();
        limitPage = (int)Math.ceil ( count / pageSize );
        allCH = chuHuoDanService.findAllByCountName (moHuName,(pageNum - 1) * pageSize, pageSize );
        model.addAttribute ( "moHuName",moHuName );
        model.addAttribute ( "list",allCH);
        model.addAttribute ("pageNum",pageNum);
        model.addAttribute ("firstPage",1);
        model.addAttribute ("lastPage",limitPage);
        return "show.jsp";
    }


    
	@RequestMapping("daoRuExcelById")
    public String daoRuExcelById(Integer id) throws Exception{
    	chuHuoDanById = chuHuoDanService.findChuHuoDanById(id);
    	int suiJiShu=(int)((Math.random()*9+1)*100000);
    	xlsFile = new File(chuHuoDanById.getSiji()+suiJiShu+".xls");
    	//创建一个工作薄sheet
   		workbook = Workbook.createWorkbook(xlsFile);
   		//查看Excel存放路径
   		System.out.println(xlsFile.getAbsolutePath());
				//创建一个工作表
				WritableSheet sheetOne = workbook.createSheet("sheet1", 0);
				/** 
	             * 定义单元格样式 
	             */  
				WritableFont wf_qx = new WritableFont(WritableFont.ARIAL, 18,  
		                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	            WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 13,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	            WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 13,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	            WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 13,  
	                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                    jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
	  
	            
	            
	            WritableCellFormat wcf_qx = new WritableCellFormat(wf_qx); // 单元格定义  
	            wcf_qx.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色  
	            wcf_qx.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
	            wcf_qx.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	            
	            
	            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义  
	            wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色  
	            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
	            wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
	           
	            WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title); // 单元格定义  
	        //    wcf_title1.setBackground(jxl.format.Colour.LIGHT_GREEN); // 设置单元格的背景颜色  
	            wcf_title1.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
	            wcf_title1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
	              
	            WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title); // 单元格定义  
	       //  wcf_title2.setBackground(jxl.format.Colour.YELLOW2); // 设置单元格的背景颜色  
	            wcf_title2.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
	            wcf_title2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
	  
	            WritableCellFormat wcf_head1 = new WritableCellFormat(wf_head);   
	      //     wcf_head1.setBackground(jxl.format.Colour.LIGHT_GREEN);  
	            wcf_head1.setAlignment(jxl.format.Alignment.CENTRE);   
	            wcf_head1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);   
	              
	            WritableCellFormat wcf_head2 = new WritableCellFormat(wf_head);   
 	      //    wcf_head2.setBackground(jxl.format.Colour.YELLOW2);  
	            wcf_head2.setAlignment(jxl.format.Alignment.CENTRE);   
	            wcf_head2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);   
	  
	  
	            WritableCellFormat wcf_table1 = new WritableCellFormat(wf_table);   
	   	  //    wcf_table1.setBackground(jxl.format.Colour.LIGHT_GREEN);   
	            wcf_table1.setAlignment(jxl.format.Alignment.CENTRE);   
	            wcf_table1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);   
	              
	            WritableCellFormat wcf_table2 = new WritableCellFormat(wf_table);   
	  	  //    wcf_table2.setBackground(jxl.format.Colour.YELLOW2);   
	            wcf_table2.setAlignment(jxl.format.Alignment.CENTRE);   
	            wcf_table2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);   
	            
	            WritableCellFormat wcf_beizhu = new WritableCellFormat(wf_head);   

	            wcf_beizhu.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); 
	            
	            sheetOne.setColumnView(0, 20); // 设置列的宽度  
	            sheetOne.setColumnView(1, 20); // 设置列的宽度  
	            sheetOne.setColumnView(2, 20); // 设置列的宽度  
	            sheetOne.setColumnView(3, 20); // 设置列的宽度  
	            sheetOne.setColumnView(4, 20); // 设置列的宽度  
	            sheetOne.setColumnView(5, 20); // 设置列的宽度  
	            sheetOne.setColumnView(6, 20); // 设置列的宽度  
	            sheetOne.setColumnView(7, 20); // 设置列的宽度  
	            sheetOne.setColumnView(8, 20); // 设置列的宽度  
	            sheetOne.setColumnView(9, 20); // 设置列的宽度  
	            sheetOne.setColumnView(10, 20); // 设置列的宽度  
	            sheetOne.setColumnView(11, 20); // 设置列的宽度  
	            sheetOne.setColumnView(12, 20); // 设置列的宽度  
	            sheetOne.setColumnView(13, 20); // 设置列的宽度
				
	          //在Label对象的构造子中指名单元格位置是第一列第一行(0,0)   
	            //以及单元格内容为test   
	            Label title=new Label(0,0,"淇县信誉商砼有限公司送货单",wcf_qx);  
	            //Label titleOne=new Label(0,1,"编号",wcf_title1);  
	            Label titleTwo=new Label(0,1,"编号",wcf_title1);	           
	            Label titleTwo1=new Label(4,1,"时间:",wcf_title2);  	           
	            Label titleTwo2= new Label(6,1,"计量单位",wcf_title1);	       
	            Label titleTwoValue=new Label(1,1,chuHuoDanById.getId().toString(),wcf_beizhu);
	            Label titleTwo1Value=new Label(5,1,chuHuoDanById.getCreatetime(),wcf_title1);
	            Label titleTwo2Value=new Label(7,1,"Kg",wcf_title1);
	            
	            Label three1=new Label(0,2,"收货单位",wcf_head1);  
	            Label three2=new Label(4,2,"等级强度",wcf_head1);  
	            Label three3=new Label(6,2,"浇筑方式",wcf_head2);  
	            Label three1Value=new Label(1,2,chuHuoDanById.getShouhuodanwei(),wcf_beizhu);  
	            Label three2Value=new Label(5,2,chuHuoDanById.getDengjiqiangdu(),wcf_head2);  
	            Label three3Value=new Label(7,2,chuHuoDanById.getJiaozhufangshi(),wcf_head2);  
	       
	            Label four1=new Label(0,3,"工程名称",wcf_head1);  
	            Label four2=new Label(4,3,"坍塌度",wcf_head1);  
	            Label four3=new Label(6,3,"抗渗等级",wcf_head2);  
	            Label four1Value=new Label(1,3,chuHuoDanById.getGongchengname(),wcf_beizhu);  
	            Label four2Value=new Label(5,3,chuHuoDanById.getTantadu(),wcf_head2);  
	            Label four3Value=new Label(7,3,chuHuoDanById.getKangshendengji(),wcf_head2);  	  
	  
	            Label five1=new Label(0,4,"施工部位",wcf_head1);  
	            Label five2=new Label(4,4,"现场坍塌度",wcf_head1);  
	            Label five3=new Label(6,4,"到达时间",wcf_head2);  
	            Label five1Value=new Label(1,4,chuHuoDanById.getShigongbuwei(),wcf_beizhu);  
	            Label five2Value=new Label(5,4,chuHuoDanById.getXianchangtantadu(),wcf_head2);  
	            Label five3Value=new Label(7,4,chuHuoDanById.getDaodatime(),wcf_head2);  
	            
	            Label six1=new Label(0,5,"车号",wcf_head1);  
	            Label six2=new Label(2,5,"司机",wcf_head1);  
	            Label six3=new Label(4,5,"车次",wcf_head2);  
	            Label six4=new Label(6,5,"本次方量",wcf_head2);  
	            Label six1Value=new Label(1,5,chuHuoDanById.getChehao(),wcf_head2);  
	            Label six2Value=new Label(3,5,chuHuoDanById.getSiji(),wcf_head2);  
	            Label six3Value=new Label(5,5,chuHuoDanById.getCheci().toString(),wcf_head2);  	            
	            Label six4Value=new Label(7,5,chuHuoDanById.getBencifangliang().toString(),wcf_head2);  	            
	            
	            
	            Label seven1=new Label(0,6,"毛重",wcf_head1);  
	            Label seven2=new Label(2,6,"皮重",wcf_head1);  
	            Label seven3=new Label(4,6,"净重",wcf_head2);  
	            Label seven4=new Label(6,6,"累计方量",wcf_head2);  
	            Label seven1Value=new Label(1,6,chuHuoDanById.getMaozhong().toString(),wcf_head2);  
	            Label seven2Value=new Label(3,6,chuHuoDanById.getPizhong().toString(),wcf_head2);  
	            Label seven3Value=new Label(5,6,chuHuoDanById.getJingzhong().toString(),wcf_head2);  	            
	            Label seven4Value=new Label(7,6,chuHuoDanById.getLeiji().toString(),wcf_head2);  	            
	            
	            
	            Label eight1=new Label(0,7,"备注",wcf_head2);  
	            Label eight1Value=new Label(1,7,chuHuoDanById.getBeizhu(),wcf_beizhu);  
	            
	            //或者WritableCell cell =  new jxl.write.Number(column, row, value, wcf)  
	            //将定义好的单元格添加到工作表中   
	            sheetOne.addCell(title);   
	            sheetOne.addCell(titleTwo);   
	            sheetOne.addCell(titleTwo1);   
	            sheetOne.addCell(titleTwo2);   
	            sheetOne.addCell(titleTwoValue);   
	            sheetOne.addCell(titleTwo1Value);   
	            sheetOne.addCell(titleTwo2Value);   
	            	
	            sheetOne.addCell(three1);   
	            sheetOne.addCell(three2);   
	            sheetOne.addCell(three3);   
	            sheetOne.addCell(three1Value);   
	            sheetOne.addCell(three2Value);   
	            sheetOne.addCell(three3Value);   
	            
	            sheetOne.addCell(four1);
	            sheetOne.addCell(four2);
	            sheetOne.addCell(four3);
	            sheetOne.addCell(four1Value);
	            sheetOne.addCell(four2Value);
	            sheetOne.addCell(four3Value);

	            sheetOne.addCell(five1);	            
	            sheetOne.addCell(five2);	            
	            sheetOne.addCell(five3);	            
	            sheetOne.addCell(five1Value);	            
	            sheetOne.addCell(five2Value);	            
	            sheetOne.addCell(five3Value);	            
	                        
	            sheetOne.addCell(six1);
	            sheetOne.addCell(six2);
	            sheetOne.addCell(six3);
	            sheetOne.addCell(six4);
	            sheetOne.addCell(six1Value);
	            sheetOne.addCell(six2Value);
	            sheetOne.addCell(six3Value);
	            sheetOne.addCell(six4Value);

	            sheetOne.addCell(seven1);
	            sheetOne.addCell(seven2);
	            sheetOne.addCell(seven3);
	            sheetOne.addCell(seven4);
	            sheetOne.addCell(seven1Value);
	            sheetOne.addCell(seven2Value);
	            sheetOne.addCell(seven3Value);
	            sheetOne.addCell(seven4Value);

	            sheetOne.addCell(eight1);
	            sheetOne.addCell(eight1Value);
	            
	          //合并单元格，
	          //第1个参数：要合并的单元格最左上角的列号，
	          //第二个参数：要合并的单元格最左上角的行号，
	          //第三个参数：要合并的单元格最右角的列号，
	          //第四个参数：要合并的单元格最右下角的行号，
	            sheetOne.mergeCells(0, 0, 7, 0);
	            sheetOne.mergeCells(1, 1, 3, 1);
	            sheetOne.mergeCells(1, 2, 3, 2);
	            sheetOne.mergeCells(1, 3, 3, 3);
	            sheetOne.mergeCells(1, 4, 3, 4);
	          
	            sheetOne.mergeCells(0, 7, 0, 8);
	            sheetOne.mergeCells(1, 7, 7, 8);
	            
					workbook.write();
					workbook.close();

    	return "redirect:/findAll.do";
    }
    
    
	
}
