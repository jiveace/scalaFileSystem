package org.iam.fp.commands

import org.iam.fp.files.{DirEntry, File}
import org.iam.fp.filesystem.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
