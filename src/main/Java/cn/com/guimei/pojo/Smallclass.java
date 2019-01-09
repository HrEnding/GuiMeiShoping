package cn.com.guimei.pojo;


public class Smallclass {

  private Long id;
  private String smSName;
  private Long smBId;
  private String smText;

  @Override
  public String toString() {
    return "Smallclass{" +
            "id=" + id +
            ", smSName='" + smSName + '\'' +
            ", smBId=" + smBId +
            ", smText='" + smText + '\'' +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getSmSName() {
    return smSName;
  }

  public void setSmSName(String smSName) {
    this.smSName = smSName;
  }


  public Long getSmBId() {
    return smBId;
  }

  public void setSmBId(Long smBId) {
    this.smBId = smBId;
  }


  public String getSmText() {
    return smText;
  }

  public void setSmText(String smText) {
    this.smText = smText;
  }

}
