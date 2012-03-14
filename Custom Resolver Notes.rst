.. contents::

=======================
IvyDE Custom Downloader
=======================

General Information
===================

This document details plans for the use of a custom downloader in IvyDE.
The settings that can be configured are, for now, tightly coupled with a
build of Ivy - such that the ability to specify a custom downloader must be supported
by the Ivy build packaged with this plugin.

The purpose of this document is to serve as a storage place for all related development ideas.
It will cover technical as well as more abstract concepts, with the end goal of making the custom
properties, and associated design decisions clearer to the user.

The need for the option to specify a custom downloader has arisen out of a series of unfortunate circumstances.
Namely, a workspace I develop in has a remote repository from which it fetches artifacts at development time.
This repository requires that the person resolving be connected to a VPN with the company. Unfortunately, the
VPN software available for this purpose (or the connection I'm using - either at my end, or the remote point)
is flaky at best. It's not entirely uncommon for there to be lost packets, or even partial disconnects.

Ivy does not handle this well at present. The InputStream class simply hangs on a Read, and the only way to
circumvent the application from locking up forever is to implement a timeout. Now, it might seem that the
obvious option after establishing that a timeout has occurred, would be to check how much of the file
was downloaded, and to attempt to reconnect to the server, requesting the rest of the file.

According to the `HTTP/1.1 Specification`__, the server should support the Range header - unfortunately, the one
I'm working with doesn't. The other option investigated was to simply skip over any unneeded bytes -
obviously, this is just the same as redownloading the file again; meaning the chance of the file being
downloaded successfully is up to the whims of fate.

__ http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html#sec10.2.7

Configurable Settings
=====================

The main way the plugin is expanded, is via the addition of some extra fields in the *Retrieve* pane.
The following controls are new editions:

======================= ================================================= ================
**Field**               **Purpose**                                       **Control Type**
----------------------- ------------------------------------------------- ----------------
Use custom downloader   Enables the rest of the options.                  Checkbox
Dowloader path          Specify the program to be used in file retrieval. Textfield
Downloader arguments    Provide custom arguments to the downloader.       Textfield
======================= ================================================= ================
