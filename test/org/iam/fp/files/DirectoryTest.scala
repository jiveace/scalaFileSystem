package org.iam.fp.files

import org.scalatest.flatspec.AnyFlatSpec

class DirectoryTest extends AnyFlatSpec {

  val parentPath = ".."
  val name = "test"
  val BLANK = ""

  "constructor" must "create a directory object" in {
    val testDirectory = new Directory(parentPath, name, List())
    assert(testDirectory.parentPath === parentPath)
    assert(testDirectory.name === name)
    assert(testDirectory.contents.isEmpty)
  }

  "empty" must "create a directory object with an empty contents list" in {
    val testDirectory = Directory.empty(parentPath, name)
    assert(testDirectory.parentPath === parentPath)
    assert(testDirectory.name === name)
    assert(testDirectory.contents.isEmpty)
  }

  "ROOT" must "create a directory object with a blank path, blank name and empty contents list" in {
    val testDirectory = Directory.ROOT
    assert(testDirectory.parentPath === BLANK)
    assert(testDirectory.name === BLANK)
    assert(testDirectory.contents.isEmpty)
  }
}
