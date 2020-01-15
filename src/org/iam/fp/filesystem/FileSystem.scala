package org.iam.fp.filesystem

import java.util.Scanner

import org.iam.fp.commands.Command
import org.iam.fp.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    state = Command.from(scanner.nextLine())(state)
  }
}
