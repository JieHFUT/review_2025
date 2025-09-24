package jiehfut.schedule.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: SysSchedule
 * Package: jie.hfut.schedule.pojo
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 22:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysSchedule implements Serializable {
    private static final long serialVersionUID = 1439856431256897L;

    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;

}
