// sbt 1.1.x
enablePlugins(Antlr4Plugin)

antlr4Version in Antlr4 := "4.7.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

fork := true

//javaOptions += "-Xverify:none"
javaOptions += "-Xverify:all"

antlr4GenListener in Antlr4 := false
antlr4GenVisitor in Antlr4 := true 

