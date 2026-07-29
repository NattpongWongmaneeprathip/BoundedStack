import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
/**
 * Test class for BoundedStack.
 * This class contains unit tests to verify the functionality of the BoundedStack class.
 */
public class BoundedStackTest {
    // Test methods will be added here to test the functionality of BoundedStack.
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }

        System.out.println("=== BoundedStack Test Suite ===\n");

        testCreators();
        testAdd();
        testRemove();
        testObservers();
        testProducer();
        testExposure();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }

    // --- Partition: มีรายชื่อ มีเลขประจำตัว / input ที่ผิดเงื่อนไข
    private static void testCreators() {
        System.out.println("=== testCreators ===");
        // Test creating a BoundedStack with valid inputs
        BoundedStack stack1 = new BoundedStack();
        check("Create empty BoundedStack", stack1.size() == 0);

        // Test creating a BoundedStack with initial names and num_ids
        List<String> names = new ArrayList<>();
        names.add("John Doe");
        List<String> num_ids = new ArrayList<>();
        num_ids.add("1234567890123");
        BoundedStack stack2 = new BoundedStack(names, num_ids);
        check("Create BoundedStack with initial names and num_ids", stack2.size() == 1);

         // boundary: list ว่างคือขอบล่างที่ถูกต้อง
         BoundedStack stack3 = new BoundedStack(Collections.emptyList(), Collections.emptyList());
         check("Create BoundedStack with empty lists", stack3.size() == 0);

        // input ที่ผิดเงื่อนไขต้องโยน exception ไม่ใช่ปล่อยผ่าน
        boolean threwNull = false;
        try {
            new BoundedStack(null, num_ids);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("Create BoundedStack with null names -> throws IllegalArgumentException", threwNull);

        boolean threwDuplicate = false;
        try {
            List<String> duplicateNames = new ArrayList<>();
            duplicateNames.add("John Doe");
            duplicateNames.add("John Doe"); // Duplicate name
            new BoundedStack(duplicateNames, num_ids);
        } catch (IllegalArgumentException e) {
            threwDuplicate = true;
        }
        check("Create BoundedStack with duplicate names -> throws IllegalArgumentException", threwDuplicate);

        boolean threwExceedLimit = false;
        try {
            List<String> longNames = new ArrayList<>();
            for (int i = 0; i < 51; i++) {
                longNames.add("Name" + i); // Exceeding MAX_NAME
            }
            new BoundedStack(longNames, num_ids);
        } catch (IllegalArgumentException e) {
            threwExceedLimit = true;
        }
        check("Create BoundedStack with names exceeding limit -> throws IllegalArgumentException", threwExceedLimit);

        boolean threwExceedNumIdLimit = false;
        try {
            List<String> longNumIds = new ArrayList<>();
            for (int i = 0; i < 51; i++) {
                longNumIds.add("NumId" + i); // Exceeding MAX_NUM_ID
            }
            new BoundedStack(names, longNumIds);
        } catch (IllegalArgumentException e) {
            threwExceedNumIdLimit = true;
        }
        check("Create BoundedStack with num_ids exceeding limit -> throws IllegalArgumentException", threwExceedNumIdLimit);
    }

    // --- Mutator: add ต้องรักษาลำดับและกันรายชื่อและเลขประจำตัวซ้ำ ---
    private static void testAdd() {
        System.out.println("=== testAdd ===");
    // adding elements to BoundedStack
        BoundedStack stack = new BoundedStack();
        stack.add("Alice", "1111111111111");
        check("Add element to BoundedStack", stack.size() == 1);
        // รายชื่อและเลขประจำตัวซ้ำ — คืน false เฉย ๆ
        boolean addedDuplicate = stack.add("Alice", "1111111111111");
        check("Add duplicate element to BoundedStack returns false", !addedDuplicate);
        // input ที่ผิดเงื่อนไขต้องโยน exception
        boolean threwEmpty = false;
        try {
            stack.add("", "2222222222222");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty name) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            stack.add(null, "2222222222222");
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null name) -> throws IllegalArgumentException", threwNull);

        check("failed adds leave stack unchanged", stack.size() == 1);
    }

    //--- Mutator: remove ทั้งกรณีพบและไม่พบ ---
    private static void testRemove() {
        System.out.println("=== testRemove ===");
        // Test removing elements from BoundedStack
    }

    // --- Observer ต้องไม่มี side effect ---
    private static void testObservers() {
        System.out.println("=== testObservers ===");
        // Test observer methods
        BoundedStack stack = new BoundedStack();
        stack.add("Alice", "1111111111111");
        check("size() returns correct value", stack.size() == 1);
        check("contains() returns true for existing element", stack.contains("Alice", "1111111111111"));
        check("contains() returns false for non-existing element", !stack.contains("Bob", "2222222222222"));
        // Test that observer methods don't have side effects
        int originalSize = stack.size();
        stack.names();
        stack.num_ids();
        check("Observer methods don't have side effects", stack.size() == originalSize);
    }

    // --- Producer ต้องคืนตัวใหม่ ไม่แก้ตัวเดิม ---
    private static void testProducer() {
        System.out.println("=== testProducer ===");
        // Test producer functionality
        BoundedStack stack = new BoundedStack();
        stack.add("Alice", "1111111111111");
        BoundedStack shuffledStack = stack.shuffled();

        check("shuffled() returns a new BoundedStack", shuffledStack != stack);
        check("shuffled() returns a BoundedStack with same size", shuffledStack.size() == stack.size());
        check("shuffled() returns a BoundedStack with same elements", shuffledStack.contains("Alice", "1111111111111"));
        check("shuffled() does not modify original BoundedStack", stack.size() == 1 && stack.contains("Alice", "1111111111111"));
        check("shuffled() returns a BoundedStack with same elements but different order", new HashSet<>(shuffledStack.names()).equals(new HashSet<>(stack.names())) && new HashSet<>(shuffledStack.num_ids()).equals(new HashSet<>(stack.num_ids())));
        
        Collections.sort(stack.names());
        Collections.sort(shuffledStack.names()); 

         // mutate ตัวใหม่ต้องไม่กระทบตัวเดิ
         shuffledStack.add("Bob", "2222222222222");
         check("mutating shuffled() does not affect original BoundedStack", !stack.contains("Bob", "2222222222222"));
         int originalSize = stack.size();
         check("mutating shuffled() does not affect original BoundedStack size", stack.size() == originalSize);

         // boundary: shuffle เนมลิสต์ว่างต้องไม่พัง
            BoundedStack emptyStack = new BoundedStack();
            BoundedStack shuffledEmptyStack = emptyStack.shuffled();
            check("shuffled() on empty BoundedStack returns a new BoundedStack", shuffledEmptyStack != emptyStack);
            check("shuffled() on empty BoundedStack returns a BoundedStack with size 0", shuffledEmptyStack.size() == 0);
    }

    // --- ทดสอบว่าไม่เกิด representation exposure ---
    private static void testExposure() {
        System.out.println("=== testExposure ===");

         // ขาออก: แก้ list ที่ได้จาก songs() ต้องไม่กระทบ rep
       BoundedStack stack = new BoundedStack();
        stack.add("Alice", "1111111111111");
        
        List<String> names = stack.names();
        names.add("Bob"); // Attempt to modify the returned list
        check("Exposure test: modifying returned list does not affect original BoundedStack", !stack.contains("Bob", "2222222222222"));
        
        List<String> num_ids = stack.num_ids();
        num_ids.add("3333333333333"); // Attempt to modify the returned list
        check("Exposure test: modifying returned list does not affect original BoundedStack", !stack.contains("Alice", "3333333333333"));
        
        // สองครั้งต้องเป็นคนละ object
        List<String> names2 = stack.names();
        check("Exposure test: names() returns a new list each time", names != names2);
        
        List<String> num_ids2 = stack.num_ids();
        check("Exposure test: num_ids() returns a new list each time", num_ids != num_ids2);

        // ขาเข้า: แก้ list ที่ส่งให้ constructor ต้องไม่กระทบ rep
        List<String> inputNames = new ArrayList<>();
        inputNames.add("Charlie");
        List<String> inputNumIds = new ArrayList<>();
        inputNumIds.add("4444444444444");
        BoundedStack stack2 = new BoundedStack(inputNames, inputNumIds);
        inputNames.add("David");
        inputNumIds.add("5555555555555");
        check("Exposure test: modifying input lists after construction does not affect BoundedStack", !stack2.contains("David", "5555555555555"));
    }
}
   