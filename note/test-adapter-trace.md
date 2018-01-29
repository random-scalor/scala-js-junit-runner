
```
AAA com.carrotgarden.sjs.junit.ScalaJS_SuiteTest$Suite01
AAA com.carrotgarden.sjs.junit.ScalaJS_SuiteTest$Suite02
AAA com.carrotgarden.sjs.junit.ScalaJS_SuiteTest$Suite03
@@@ setup
XXX config
XXX runtime
XXX classpath
Linker: Compute reachability: 304191 us
Linker: Assemble LinkedClasses: 426981 us
Basic Linking: 757823 us
Inc. optimizer: Batch mode: true
Inc. optimizer: Incremental part: 201539 us
Inc. optimizer: Optimizing 7846 methods.
Inc. optimizer: Optimizer part: 617930 us
Inc. optimizer: 836846 us
Refiner: Compute reachability: 57007 us
Refiner: Assemble LinkedClasses: 13187 us
Refiner: 71952 us
Emitter: Class tree cache stats: reused: 672 -- invalidated: 684
Emitter: Method tree cache stats: resued: 0 -- invalidated: 3748
Emitter (write output): 699550 us
BBB com.carrotgarden.sjs.junit.test.Test01
############################
java.lang.Error: Z
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM(JSEnv.scala:80)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM$(JSEnv.scala:79)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$AsyncNodeRunner.startVM(JSEnv.scala:104)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.startExternalJSEnv(ExternalJSEnv.scala:211)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.start(ExternalJSEnv.scala:199)
    at org.scalajs.testadapter.ScalaJSFramework.fetchFrameworkInfo(ScalaJSFramework.scala:65)
    at org.scalajs.testadapter.ScalaJSFramework.<init>(ScalaJSFramework.scala:38)
    at com.carrotgarden.sjs.junit.Context.makeTester(Context.scala:95)
    at com.carrotgarden.sjs.junit.Context.$anonfun$cachedTester$1(Context.scala:103)
    at scala.collection.concurrent.TrieMap.getOrElseUpdate(TrieMap.scala:903)
    at com.carrotgarden.sjs.junit.Context.cachedTester(Context.scala:103)
    at com.carrotgarden.sjs.junit.Context.defaultTester$lzycompute(Context.scala:111)
    at com.carrotgarden.sjs.junit.Context.defaultTester(Context.scala:110)
    at com.carrotgarden.sjs.junit.Context.defaultFramework$lzycompute(Context.scala:115)
    at com.carrotgarden.sjs.junit.Context.defaultFramework(Context.scala:114)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.framework$lzycompute(ScalaJS_Suite.scala:29)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.framework(ScalaJS_Suite.scala:29)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:58)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:25)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.junit.runners.Suite.runChild(Suite.java:128)
    at org.junit.runners.Suite.runChild(Suite.java:27)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
    at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)
XXX Starting process: ./test-tool/node/node
java.lang.Error: Z
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM(JSEnv.scala:80)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM$(JSEnv.scala:79)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$AsyncNodeRunner.startVM(JSEnv.scala:104)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.startExternalJSEnv(ExternalJSEnv.scala:211)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.start(ExternalJSEnv.scala:199)
    at org.scalajs.testadapter.ScalaJSRunner.createRemoteRunner(ScalaJSRunner.scala:177)
    at org.scalajs.testadapter.ScalaJSRunner.<init>(ScalaJSRunner.scala:50)
    at org.scalajs.testadapter.ScalaJSFramework.runner(ScalaJSFramework.scala:56)
    at com.carrotgarden.sjs.junit.Context.defaultRunner$lzycompute(Context.scala:119)
    at com.carrotgarden.sjs.junit.Context.defaultRunner(Context.scala:118)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.testerRunner$lzycompute(ScalaJS_Suite.scala:31)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.testerRunner(ScalaJS_Suite.scala:31)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:69)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:25)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.junit.runners.Suite.runChild(Suite.java:128)
    at org.junit.runners.Suite.runChild(Suite.java:27)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
    at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)
XXX Starting process: ./test-tool/node/node
java.lang.Error: Z
XXX Starting process: ./test-tool/node/node
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM(JSEnv.scala:80)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$CustomRunner.startVM$(JSEnv.scala:79)
    at com.carrotgarden.sjs.junit.JSEnv$NodeBasic$AsyncNodeRunner.startVM(JSEnv.scala:104)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.startExternalJSEnv(ExternalJSEnv.scala:211)
    at org.scalajs.jsenv.ExternalJSEnv$AsyncExtRunner.start(ExternalJSEnv.scala:199)
    at org.scalajs.testadapter.ScalaJSRunner.createSlave(ScalaJSRunner.scala:128)
    at org.scalajs.testadapter.ScalaJSRunner.$anonfun$getSlave$1(ScalaJSRunner.scala:119)
    at scala.collection.MapLike.getOrElse(MapLike.scala:128)
    at scala.collection.MapLike.getOrElse$(MapLike.scala:126)
    at scala.collection.concurrent.TrieMap.getOrElse(TrieMap.scala:631)
    at org.scalajs.testadapter.ScalaJSRunner.getSlave(ScalaJSRunner.scala:119)
    at org.scalajs.testadapter.ScalaJSTask.execute(ScalaJSTask.scala:31)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.$anonfun$runChild$1(ScalaJS_Suite.scala:78)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.$anonfun$runChild$1$adapted(ScalaJS_Suite.scala:77)
    at scala.collection.IndexedSeqOptimized.foreach(IndexedSeqOptimized.scala:32)
    at scala.collection.IndexedSeqOptimized.foreach$(IndexedSeqOptimized.scala:29)
    at scala.collection.mutable.ArrayOps$ofRef.foreach(ArrayOps.scala:191)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:77)
    at com.carrotgarden.sjs.junit.ScalaJS_Suite.runChild(ScalaJS_Suite.scala:25)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.junit.runners.Suite.runChild(Suite.java:128)
    at org.junit.runners.Suite.runChild(Suite.java:27)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
    at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
    at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
    at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)
Test run started
### com.carrotgarden.sjs.junit.test.Test01
Test com.carrotgarden.sjs.junit.test.Test01.verifyVM started
Test com.carrotgarden.sjs.junit.test.Test01.verifyVM finished, took 0.006000128 sec
Test run finished: 0 failed, 0 ignored, 1 total, 0.012s
BBB com.carrotgarden.sjs.junit.test.Test02
Test run started
Test com.carrotgarden.sjs.junit.test.Test02.verifyException ignored
Test run finished: 0 failed, 1 ignored, 0 total, 0.002000128s
BBB com.carrotgarden.sjs.junit.test.Test03
Test run started
### com.carrotgarden.sjs.junit.test.Test03
Test com.carrotgarden.sjs.junit.test.Test03.verifyJQuery started
Test com.carrotgarden.sjs.junit.test.Test03.verifyJQuery finished, took 0.000999936 sec
Test run finished: 0 failed, 0 ignored, 1 total, 0.003000064s
```