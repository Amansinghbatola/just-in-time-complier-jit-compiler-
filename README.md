# ⚙️ Java JIT Compiler UI (Swing-Based)

This project simulates a basic **Just-In-Time (JIT) Compiler** using a GUI built with **Java Swing**. It lets users enter Java method body code, compiles it dynamically, shows simulated compilation phases, and executes the code — all in real-time.

> 🔍 Decompiled from `.class` using FernFlower decompiler.

---

## 🚀 Features

- ✅ GUI-based Java code editor using Swing
- ✅ Real-time simulation of compiler phases:
  - Lexical Analysis
  - Syntax Analysis
  - Semantic Analysis
  - Intermediate Code Generation
  - Code Optimization
  - Code Generation
- ✅ Dynamic Java source compilation (`JavaCompiler` API)
- ✅ Output and errors displayed live in terminal-style area
- ✅ Measures and displays execution time

## 📁 File Structure

```plaintext
JITCompilerUI.java       # Main GUI class
CodeExecutor.java        # Handles dynamic compilation and execution
🛠 How to Run
1. Compile:
bash
Copy
Edit
javac JITCompilerUI.java CodeExecutor.java
2. Run:
bash
Copy
Edit
java JITCompilerUI
Make sure you have JDK 8 or higher installed.

✍️ How It Works
You write Java code inside the method body (static void run()).

The code is wrapped into a full class DynamicClass.

It's saved to DynamicClass.java and compiled using the Java Compiler API.

The bytecode is loaded using URLClassLoader.

The method is invoked reflectively, and output is captured and displayed.

⚠️ Note
Only the method body (statements inside run() method) should be written.

Do not declare a full class or main method.

Intended for educational/demo purposes, not secure for untrusted code.

📦 Requirements
Java Development Kit (JDK 8+)

Any platform supporting Java (Windows/Linux/Mac)

IDE (optional): IntelliJ, Eclipse, or Visual Studio Code

📄 License
This project is released under the MIT License.

👨‍💻 Author
Aman Singh Batola
📧 GitHub Profile  @Amansinghbatola

🏁 Example Input (Inside the app)
java
Copy
Edit
int a = 5, b = 10;
System.out.println("Sum = " + (a + b));
Output:

makefile
Copy
Edit
Sum = 15
⏱️ Time taken: 120 ms
📬 Contribution
Pull requests and feedback are welcome!
Feel free to fork the repo and improve the UI, add support for user-defined methods, or add static analysis.

yaml
Copy
Edit

---

Let me know if you want:
- A `LICENSE` file
- Markdown badges (build, Java version, etc.)
- A `.gitignore` tailored for this project

I'll help you push this with a `README.md` file directly to your GitHub repo if needed.
