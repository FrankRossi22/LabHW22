import components.set.Set;
import components.set.Set1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                // TODO - fill in case
                int length = s.lengthOfBlock();
                for (int i = 0; i < length; i++) {
                    Statement block = s.removeFromBlock(i);

                    count += countOfPrimitiveCalls(block);
                    s.addToBlock(i, block);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                // TODO - fill in case
                Statement block = new Statement1();
                Condition c = s.disassembleIf(block);
                count += countOfPrimitiveCalls(block);
                s.assembleIf(c, block);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case
                Statement blockOne = new Statement1();
                Statement blockTwo = new Statement1();

                Condition c = s.disassembleIfElse(blockOne, blockTwo);
                count += countOfPrimitiveCalls(blockOne)
                        + countOfPrimitiveCalls(blockTwo);
                s.assembleIfElse(c, blockOne, blockTwo);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                // TODO - fill in case
                Statement block = new Statement1();
                Condition c = s.disassembleWhile(block);
                count += countOfPrimitiveCalls(block);
                s.assembleWhile(c, block);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case
                Set<String> primID = new Set1L<String>();
                primID.add("move");
                primID.add("turnleft");
                primID.add("turnright");
                primID.add("infect");
                primID.add("skip");
                String call = s.disassembleCall();
                if (primID.contains(call)) {
                    count++;
                }
                s.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
