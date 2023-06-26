import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        // Step 1: Find pairs of integers that sum up to the target value
        List<List<Integer>> pairs = findPairs(nums, target);
        System.out.println("First Combination for \"" + target + "\": " + pairs);

        // Step 2: Merge the pairs into a single sorted array
        int[] mergedArray = mergeAndSort(pairs);
        System.out.println("Merge Into a Single Array: " + Arrays.toString(mergedArray));

        // Step 3: Double the target value
        int doubledTarget = target * 2;
        System.out.println("Doubled Target Value: " + doubledTarget);

        // Step 4: Find combinations of digits that sum up to the doubled target value
        List<List<Integer>> combinations = findCombinations(mergedArray, doubledTarget);
        System.out.println("Second Combination for \"" + doubledTarget + "\": " + combinations);
    }

    // Step 1: Find pairs of integers that sum up to the target value
    private static List<List<Integer>> findPairs(int[] nums, int target) {
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    List<Integer> pair = Arrays.asList(nums[i], nums[j]);
                    pairs.add(pair);
                }
            }
        }

        return pairs;
    }

    // Step 2: Merge the pairs into a single sorted array
    private static int[] mergeAndSort(List<List<Integer>> pairs) {
        List<Integer> merged = new ArrayList<>();

        for (List<Integer> pair : pairs) {
            merged.addAll(pair);
        }

        int[] mergedArray = new int[merged.size()];
        for (int i = 0; i < merged.size(); i++) {
            mergedArray[i] = merged.get(i);
        }

        Arrays.sort(mergedArray);
        return mergedArray;
    }

    // Step 4: Find combinations of digits that sum up to the target value
    private static List<List<Integer>> findCombinations(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        findCombinationsRecursive(nums, target, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void findCombinationsRecursive(int[] nums, int target, int start, List<Integer> currentCombination,
                                                  List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(currentCombination));
        } else if (target < 0) {
            return;
        } else {
            for (int i = start; i < nums.length; i++) {
                currentCombination.add(nums[i]);
                findCombinationsRecursive(nums, target - nums[i], i, currentCombination, combinations);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}