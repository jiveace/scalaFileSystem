package org.iam.fp.commands

import org.iam.fp.files.Directory
import org.iam.fp.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean =
    name.contains('.')


  def doMkdir(state: State, name: String): State = ???

  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hesEntry(name)) {
      state.setMessage(s"Entry $name already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain separators")

    } else if(checkIllegal(name)) {
      state.setMessage(name + ": illegal entry name!")
    }else {
      doMkdir(state, name)
    }
  }
}