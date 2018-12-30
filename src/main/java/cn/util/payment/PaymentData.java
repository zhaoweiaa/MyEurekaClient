package cn.util.payment;

import cn.util.payment.DetailData;
import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.MiniTableRenderData;

public class PaymentData {

    private MiniTableRenderData order;

    private String ID;

    private String NO;

    private String taitou;

    private String consignee;

    @Name("detail_table")
    private DetailData detailTable;

    private String subtotal;

    private String tax;

    private String transform;

    private String other;

    private String unpay;

    private String total;

    public PaymentData() {
    }

    public MiniTableRenderData getOrder() {
        return order;
    }

    public void setOrder(MiniTableRenderData order) {
        this.order = order;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getTaitou() {
        return taitou;
    }

    public void setTaitou(String taitou) {
        this.taitou = taitou;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public DetailData getDetailTable() {
        return detailTable;
    }

    public void setDetailTable(DetailData detailTable) {
        this.detailTable = detailTable;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTransform() {
        return transform;
    }

    public void setTransform(String transform) {
        this.transform = transform;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUnpay() {
        return unpay;
    }

    public void setUnpay(String unpay) {
        this.unpay = unpay;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
