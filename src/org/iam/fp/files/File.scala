package org.iam.fp.files

import org.iam.fp.filesystem.FileSystemException

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def getType: String = "File"

  override def asFile: File = this

  override def asDirectory: Directory =
    throw new FileSystemException("A file cannot be converted to a directory")
}

object File {
  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, contents = "")
}