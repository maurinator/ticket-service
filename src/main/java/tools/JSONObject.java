package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.StringBuilder;


public class JSONObject {
  private HashMap<String, Object> myMap;
  public JSONObject () {
    myMap = new HashMap<String, Object>();
  }

  public void add (String key, Object value) {
    myMap.put(key, value);
  }

  public String toString () {
    Iterator it = myMap.entrySet().iterator();
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      helper(sb, entry.getKey());
      sb.append(":");
      helper(sb, entry.getValue());
      if (it.hasNext()) {
        sb.append(",");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  private void helper(StringBuilder sb, Object entry) {
    sb.append('"');
    sb.append(entry);
    sb.append('"');
  }
}
