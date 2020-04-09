package cn.eiden.hsm.dbdata;

import lombok.Data;

import java.util.List;

/**
 * @author 周晋平
 * @date 2020/4/8 10:10
 */
@Data
public class Tag {
    private String enumId;
    private String name;
    private String type;
    private String value;
    private String zhCN;
}
