package lthdt;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public interface IStream2IO {
  void input(Scanner base, Scanner spec);
  void output(OutputStreamWriter base, OutputStreamWriter spec);
}
