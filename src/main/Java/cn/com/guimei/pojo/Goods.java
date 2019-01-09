package cn.com.guimei.pojo;


public class Goods {

  private Long id;
  private String gName;
  private Long gSmId;
  private Double gMoney;
  private Long gNumber;
  private String gImg;
  private Double gCarriage;
  private Long gType;
  private Long gSeId;
  private Long gDId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getgName() {
    return gName;
  }

  public void setgName(String gName) {
    this.gName = gName;
  }

  public Long getgSmId() {
    return gSmId;
  }

  public void setgSmId(Long gSmId) {
    this.gSmId = gSmId;
  }

  public Double getgMoney() {
    return gMoney;
  }

  public void setgMoney(Double gMoney) {
    this.gMoney = gMoney;
  }

  public Long getgNumber() {
    return gNumber;
  }

  public void setgNumber(Long gNumber) {
    this.gNumber = gNumber;
  }

  public String getgImg() {
    return gImg;
  }

  public void setgImg(String gImg) {
    this.gImg = gImg;
  }

  public Double getgCarriage() {
    return gCarriage;
  }

  public void setgCarriage(Double gCarriage) {
    this.gCarriage = gCarriage;
  }

  public Long getgType() {
    return gType;
  }

  public void setgType(Long gType) {
    this.gType = gType;
  }

  public Long getgSeId() {
    return gSeId;
  }

  public void setgSeId(Long gSeId) {
    this.gSeId = gSeId;
  }

  public Long getgDId() {
    return gDId;
  }

  public void setgDId(Long gDId) {
    this.gDId = gDId;
  }

  @Override
  public String toString() {
    return "Goods{" +
            "id=" + id +
            ", gName='" + gName + '\'' +
            ", gSmId=" + gSmId +
            ", gMoney=" + gMoney +
            ", gNumber=" + gNumber +
            ", gImg='" + gImg + '\'' +
            ", gCarriage=" + gCarriage +
            ", gType=" + gType +
            ", gSeId=" + gSeId +
            ", gDId=" + gDId +
            '}';
  }
}
