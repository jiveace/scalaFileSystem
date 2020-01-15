package org.iam.fp.filesystem

import org.iam.fp.files.Directory

class State(val root: Directory, val wd: Directory, val output: String) {

  def show: Unit = {
    println(output)
    print(State.PROMPT + ": ")
  }

  def setMessage(message: String): State =
    State(root, wd, message)
}

object State {
  def apply(root: Directory, wd: Directory, output: String = ""): State =
    new State(root, wd, output)

  def PROMPT = "$"
}