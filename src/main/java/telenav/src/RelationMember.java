package telenav.src;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: spark_sample
 * @description: RM
 * @author: Lin.wang
 * @create: 2022-02-25 09:46
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationMember {
    public String relationID;
    public String memberID;
    public String memberType;
    public String memberRole;
    public int seq;

//    @Override
//    public String toString() {
//        return "RelationMember{" +
//                "relationID='" + relationID + '\'' +
//                ", memberID='" + memberID + '\'' +
//                ", memberType='" + memberType + '\'' +
//                ", memberRole='" + memberRole + '\'' +
//                ", seq=" + seq +
//                '}';
//    }
}
