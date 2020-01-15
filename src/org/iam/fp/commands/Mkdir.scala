package org.iam.fp.commands

import org.iam.fp.files.{DirEntry, Directory}
import org.iam.fp.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = {
    Directory.empty(state.wd.path, name)
  }
}