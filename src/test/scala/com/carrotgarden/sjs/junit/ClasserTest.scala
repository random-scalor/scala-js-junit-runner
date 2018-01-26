package com.carrotgarden.sjs.junit

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import org.webjars.WebJarAssetLocator;
import org.webjars.WebJarExtractor;

import org.junit.jupiter.api.Assertions._;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith( classOf[ JUnitPlatform ] )
class ClasserTest {

  @Test
  def currentTest() : Unit = {
    val classpath = Classer.currentClassPath
    println( "------------------------------------------" )
    classpath.foreach( entry => println( s"   ${entry}" ) )
    println( "------------------------------------------" )
  }

  @Test
  def extractTest() : Unit = {
    val extractor = new WebJarExtractor();
    val target = new File( "./target/webjars" );
    target.mkdirs();
    extractor.extractAllWebJarsTo( target );
  }

  @Test
  def locatorTest() : Unit = {

    val locator = new WebJarAssetLocator();
    locator.getWebJars().forEach( //
      ( key, value ) => println( s"webjar=$key version=$value" ) //
    );

    // locator.getFullPathIndex.asScala.foreach {
    // case ( key, value ) => println( s"token=${key} entry=${value}" )
    // }

    val path = locator.getFullPath( "jquery", "dist/jquery.js" );
    println( s"path ${path}" )

    // val list = locator.listAssets()
    // list.asScala.foreach { entry => println( s"entry ${entry}" ) }
  }

}
