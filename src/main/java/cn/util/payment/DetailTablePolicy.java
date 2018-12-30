package cn.util.payment;

import cn.util.payment.DetailData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Objects;

public class DetailTablePolicy extends DynamicTableRenderPolicy {

    //货品填充数据所在行
    int goodsStartRow = 2;
    //人工费填充数据所在行
    int laborsStartRow = 5;


    @Override
    public void render(XWPFTable xwpfTable, Object o) {

        if(Objects.isNull(o)) return ;

        DetailData detailData = (DetailData) o;

        //获取人工费数据
        List<RowRenderData> labors = detailData.getLabors();

        if(Objects.nonNull(labors)){

            //删除空白行人工费行数
            xwpfTable.removeRow(laborsStartRow);
            //填充数据
            for (int i = 0; i < labors.size(); i++) {
                XWPFTableRow xwpfTableRow = xwpfTable.insertNewTableRow(laborsStartRow);
                for (int j = 0; j < 7; j++) {
                    xwpfTableRow.createCell();
                }
                //横向合并单元格
                TableTools.mergeCellsHorizonal(xwpfTable,laborsStartRow,0,3);
                //渲染数据
                MiniTableRenderPolicy.renderRow(xwpfTable,laborsStartRow,labors.get(i));
            }
        }

        List<RowRenderData> goods = detailData.getGoods();
        if(Objects.nonNull(goods)){
            xwpfTable.removeRow(goodsStartRow);
            for (int i = 0; i < goods.size(); i++) {
                XWPFTableRow xwpfTableRow = xwpfTable.insertNewTableRow(goodsStartRow);
                for (int j = 0; j < 7; j++) {
                    xwpfTableRow.createCell();
                }
                MiniTableRenderPolicy.renderRow(xwpfTable,goodsStartRow,goods.get(i));
            }
        }

    }

}
