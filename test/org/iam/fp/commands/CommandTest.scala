package org.iam.fp.commands

import org.iam.fp.filesystem.{State, UnitTest}

class CommandTest extends UnitTest {

  "from" must "create an UnknownCommand" in {
    Command.from("") shouldBe a[UnknownCommand]
  }

  "A subtype of Command" must "implement apply()" in {
    assertDoesNotCompile("new Command()")
    assertCompiles("new Command() {override def apply(state: State): State = ???}")
  }
}
