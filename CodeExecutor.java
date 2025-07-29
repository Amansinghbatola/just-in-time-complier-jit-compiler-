import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class CodeExecutor {

    public static String compileAndRun(String userCode) throws Exception {
        String className = "DynamicClass";
        String methodName = "run";

        // ✅ Clean up user code to remove invisible or illegal characters
        userCode = userCode
                .replace('\u00A0', ' ')       // Replace non-breaking spaces
                .replaceAll("\\t", " ")       // Replace tabs with spaces
                .replaceAll(" +", " ")        // Normalize multiple spaces
                .replaceAll("\\r?\\n", "\n"); // Normalize line endings

        // ✅ Create complete Java class source code
        String fullCode = "public class " + className + " {\n" +
                          "    public static void " + methodName + "() {\n" +
                          "        " + userCode + "\n" +
                          "    }\n" +
                          "}";

        // ✅ Write source code to .java file
        File javaFile = new File(className + ".java");
        try (FileWriter writer = new FileWriter(javaFile)) {
            writer.write(fullCode);
        }

        // ✅ Start time tracking
        long startTime = System.currentTimeMillis();

        // ✅ Compile the Java file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, javaFile.getPath());
        if (result != 0) {
            return "❌ Compilation Failed!";
        }

        // ✅ Capture System.out output
        ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputCapture));

        // ✅ Load and execute compiled class
        try (URLClassLoader classLoader = URLClassLoader.newInstance(
                new URL[]{new File("").toURI().toURL()})) {

            Class<?> dynamicClass = Class.forName(className, true, classLoader);
            Method method = dynamicClass.getDeclaredMethod(methodName);
            method.invoke(null);

        } catch (Exception e) {
            System.setOut(originalOut); // Restore output in case of error
            return "❌ Runtime Error:\n" + e.getCause();
        }

        // ✅ End time tracking
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // ✅ Restore original System.out
        System.setOut(originalOut);

        // ✅ Clean up compiled and source files
        javaFile.delete();
        new File(className + ".class").delete();

        // ✅ Return captured output and execution time
        return outputCapture.toString() + "\n⏱️ Time taken: " + duration + " ms";
    }
}
