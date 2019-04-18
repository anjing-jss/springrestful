package org.xdemo.example.springrestful.entity;

import java.io.Serializable;
import java.util.Map;

public class RoomLocation  implements Serializable {

  private static final long serialVersionUID = 1L;
  private String userId;
  private String deviceId;
  private String room;
  private Map<String, String> ext;
  private long timestamp;

  public String getRoom() {
      return this.room;
  }

  public void setRoom(String room) {
      this.room = room;
  }

  public Map<String, String> getExt() {
      return this.ext;
  }

  public void setExt(Map<String, String> ext) {
      this.ext = ext;
  }

  public String getUserId() {
      return this.userId;
  }

  public void setUserId(String userId) {
      this.userId = userId;
  }

  public String getDeviceId() {
      return this.deviceId;
  }

  public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
  }

  public long getTimestamp() {
      return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
      this.timestamp = timestamp;
  }

  public String toString() {
      return "RoomLocation [userId=" + this.userId + ", deviceId="
              + this.deviceId + ", room=" + this.room + ", ext=" + this.ext
              + ", timestamp=" + this.timestamp + "]";
  }
  
}
