import java.util.*;
/**
 * 6821651183 ณัฐพงศ์ วงศ์มณีประทีป sec800 lab801
 * 6821651272 ธนกร รักธรรมวาที sec800 lab801
 */


/**
 * BoundedStack is a stack with a fixed capacity. It can hold a limited number of elements, and once the capacity is reached, no more elements can be added until some are removed.
 */
/**
 * Namelist - name of people who bought tickets
 * Num_id - ID number of people who bought tickets
 * 
 * ตัวอย่างการทำงาน
 *  Namelist n = new Namelist();
 *  n.add("John Doe");
 *  Num_id id = new Num_id();
 *  Num_id.add("1234567890123");
 *  system.out.println(n.get(0)); // Output: John Doe
 *  system.out.println(id.get(0)); // Output: 1234567890123
 */

public class BoundedStack{

    private static final int MAX_NAME = 50;
    private static final int MAX_NUM_ID = 13;

     // ===== representation =====
    private final List<String> names ; // private final String[] elements ;
    private final List<String> num_ids ; // private final String[] elements ;

    //Abstract function:
    // AF(names) = names รายชื่อของคนที่ซื้อตั๋ว names.get(0), names.get(1), names.get(2), ... , ตามลำดับ
    // AF(num_ids) = num_ids เลขประจำตัวของคนที่ซื้อตั๋ว num_ids.get(0), num_ids.get(1), num_ids.get(2), ... , ตามลำดับ

    //Representation invariant:
    //names != null;
    //num_ids != null;
    //ชื้อของคนที่ซื้อตั๋วไม่ซ้ำกัน
    //เลขประจำตัวของคนที่ซื้อตั๋วไม่ซ้ำกัน
    //ชื้อของคนที่ซื้อตั๋วต้องไม่เกิน 50 ชื่อ
    //เลขประจำตัวของคนที่ซื้อตั๋วต้องไม่เกิน 13 เลข

    //Safety from rep exposure:
    //สร้าง List ใหม่ทุกครั้งที่มีการเข้าถึง names และ num_ids เพื่อป้องกันการเข้าถึงโดยตรงจากภายนอก
    //การใช้ final กับ names และ num_ids เพื่อป้องกันการเปลี่ยนแปลง reference ของ List จากภายนอก

    //checkRep method:
    private void checkRep() {
        assert names != null : "names should not be null";
        assert num_ids != null : "num_ids should not be null";
        assert names.size() <= MAX_NAME : "names size exceeds maximum limit";
        assert num_ids.size() <= MAX_NUM_ID : "num_ids size exceeds maximum limit";

        Set<String> nameSet = new HashSet<>(names);
        Set<String> numIdSet = new HashSet<>(num_ids);
        assert nameSet.size() == names.size() : "Duplicate names found";
        assert numIdSet.size() == num_ids.size() : "Duplicate num_ids found";
    }

    /**
     * Constructs a BoundedStack with the specified capacity.
     *
     * @param name the maximum number of elements the stack can hold
     * @param num_id the maximum number of elements the stack can hold
     */
    
    

    /**
     * 
     * @param s
     */
    public void push(String s){



    }
}