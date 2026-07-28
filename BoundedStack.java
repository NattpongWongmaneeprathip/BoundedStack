import java.util.*;
/**
 * 6821651183 ณัฐพงศ์ วงศ์มณีประทีป sec800 lab801
 * 6821651272 ธนกร รักธรรมวาที sec800 lab801
 */

import org.w3c.dom.NameList;


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
     * Constructs a BoundedStack and initializes the internal lists.
     */
    public BoundedStack() {
        this.names = new ArrayList<>();
        this.num_ids = new ArrayList<>();
        checkRep();
    }
    
    /**
     * ====Creator====
     * สร้างรายชื่อจากชื่อคนที่ได้มา
     * ระวัง: ห้ามเก็บ reference ของ initial ตรง ๆ (rep exposure!)
     * @param initial_name รายชื่อคน ต้องไม่ซ้ำและไม่เกิน MAX_NAME
     * @param initial_numid เลขประจำตัว ต้องไม่ซ้ำและไม่เกิน MAX_NUM_ID
     * @throws IllegalArgumentException ถ้า initial ผิดเงื่อน
     */
    public BoundedStack(List<String> initial_names, List<String> initial_num_ids) {
        if (initial_names == null || initial_num_ids == null) {
            throw new IllegalArgumentException("Initial lists must not be null");
        }
        if (initial_names.size() > MAX_NAME) {
            throw new IllegalArgumentException("initial_names size exceeds maximum limit");
        }
        if (initial_num_ids.size() > MAX_NUM_ID) {
            throw new IllegalArgumentException("initial_num_ids size exceeds maximum limit");
        }

        Set<String> nameSet = new HashSet<>(initial_names);
        Set<String> numIdSet = new HashSet<>(initial_num_ids);
        if (nameSet.size() != initial_names.size()) {
            throw new IllegalArgumentException("Duplicate names found");
        }
        if (numIdSet.size() != initial_num_ids.size()) {
            throw new IllegalArgumentException("Duplicate num_ids found");
        }

        this.names = new ArrayList<>(initial_names);
        this.num_ids = new ArrayList<>(initial_num_ids);
        checkRep();
    }

    // ==== Mutators ====

    /**
     * @param name ชื่อคน ต้องไม่เป็น null และชื่อไม่ซ้ำ
     * @param numid เลขประจำตัว ต้องไม่เป็น null และเลขไม่ซ้ำ
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีชื่อนี้หรือเลขประจำตัวนี้อยู่แล้ว
     * @throws IllegalArgumentException ถ้า name หรือ numid เป็น null หรือสตริงว่าง
     */
    public boolean add(String name, String numid) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (numid == null || numid.isEmpty()) {
            throw new IllegalArgumentException("Num_id must not be null or empty");
        }
        if (names.contains(name) || num_ids.contains(numid)) {
            return false;
        }
        if (names.size() >= MAX_NAME || num_ids.size() >= MAX_NUM_ID) {
            throw new IllegalStateException("BoundedStack is full");
        }
        names.add(name);
        num_ids.add(numid);
        checkRep();
        return true;
    }
//==ยังไม่เสร็จ==
    //==== observers ====
    //คำนวณรายชื่อว่าอยู่หรือไม่
    public int size() {
        return names.size();
    }

    //ตรวจสอบว่ามีชื่อนี้หรือไม่
    public boolean contains(String name) {
        return names.contains(name);
    }

    //คืนรายชื่อทั้งหมด
    public List<String> names() {
        return new ArrayList<>(names);
    }

    public BoundedStack(List<String> initial_names) {
        if (initial_names == null) {
            throw new IllegalArgumentException("Initial list must not be null");
        }
        if (initial_names.size() > MAX_NAME) {
            throw new IllegalArgumentException("initial_names size exceeds maximum limit");
        }

        Set<String> nameSet = new HashSet<>(initial_names);
        if (nameSet.size() != initial_names.size()) {
            throw new IllegalArgumentException("Duplicate names found");
        }

        this.names = new ArrayList<>(initial_names);
        this.num_ids = new ArrayList<>();
        checkRep();
    }

    //==== Producer ====
    
    /**
     * คืนรายชื่อใหม่ที่มีชื่อเดียวกันแต่สลับลำดับ
     * ระวัง : ห้ามแก้ไขเพลย์ลิสต์เดิม (this)เด็ดขาด
     */
    public BoundedStack shuffled() {
        List<String> copy = new ArrayList<>(names);
        Collections.shuffle(copy);
        return new BoundedStack(copy);
    }

    @Override
    public String toString() {
        return names.toString();
    }
}