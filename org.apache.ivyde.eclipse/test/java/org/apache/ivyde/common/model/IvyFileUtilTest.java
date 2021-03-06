/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.ivyde.common.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.ivyde.common.ivyfile.IvyModuleDescriptorFile;

public class IvyFileUtilTest extends TestCase {
    String hibContentStr;

    public IvyFileUtilTest(String name) {
        super(name);
        byte content[];
        try {
            RandomAccessFile accessFile = new RandomAccessFile(getClass().getResource(
                "/ivy-hibernate.xml").getFile(), "r");
            content = new byte[(int) accessFile.length()];
            accessFile.read(content);
            hibContentStr = new String(content);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void testInTag() {
        IvyFile ivyFile = new IvyModuleDescriptorFile(null, "", hibContentStr);
        boolean b = ivyFile.inTag(200);
        assertEquals(b, true);
    }

    public void testGetTagName() {
        IvyFile ivyFile = new IvyModuleDescriptorFile(null, "", hibContentStr);
        String tag = ivyFile.getTagName(200);
        assertEquals("info", tag);
        tag = ivyFile.getTagName(864);
        assertEquals("description", tag);
        // tag = IvyFileUtil.getTagName(1000);
    }

    public void testGetAllAttsValues() {
        String test = "<test att1=\"value1\" att2 =\"value 2 \"  att3 =\" value3 \" att4   =   \"  4  \"";
        IvyFile ivyFile = new IvyModuleDescriptorFile(null, "", test);
        Map all = ivyFile.getAllAttsValues(1);
        assertNotNull(all);
        assertEquals(4, all.size());
        assertEquals("value1", all.get("att1"));
        assertEquals("value 2 ", all.get("att2"));
        assertEquals(" value3 ", all.get("att3"));
        assertEquals("  4  ", all.get("att4"));
    }

    public void testGetAttributeName() {
        String test = "<test nospace=\"\" 1Space =\"\"  2Space = \"\" lotofSpace   =   \"    \"";
        IvyFile ivyFile = new IvyModuleDescriptorFile(null, "", test);
        String name = ivyFile.getAttributeName(15);
        assertEquals("nospace", name);
        name = ivyFile.getAttributeName(28);
        assertEquals("1Space", name);
        name = ivyFile.getAttributeName(39);
        assertEquals("2Space", name);
        name = ivyFile.getAttributeName(60);
        assertEquals("lotofSpace", name);
    }

    public void testGetParentTagName() {
        IvyFile ivyFile = new IvyModuleDescriptorFile(null, "", hibContentStr);
        String tag = ivyFile.getParentTagName(200);
        assertEquals("ivy-module", tag);
        tag = ivyFile.getParentTagName(2000);
        assertEquals("configurations", tag);
        tag = ivyFile.getParentTagName(1981);
        assertEquals("configurations", tag);
        tag = ivyFile.getParentTagName(1000);
        assertEquals("description", tag);
        tag = ivyFile.getParentTagName(5015);
        assertNull(tag);
    }

    public void testReadyForValue() {
    }

    public void testGetStringIndex() {
    }

    public void testGetQualifier() {
    }
}
