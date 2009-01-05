#ifndef __CONTAINER_TEST_H__
#define __CONTAINER_TEST_H__

#include <com/xuggle/testutils/TestUtils.h>
#include "Helper.h"
using namespace VS_CPP_NAMESPACE;

class ContainerTest : public CxxTest::TestSuite
{
  public:
    ContainerTest();
    virtual ~ContainerTest();
    void setUp();
    void tearDown();
    void testCreationAndDescruction();
    void testOpenForWriting();
    void testOpenForReading();
    void testInvalidOpenForWriting();
    void testInvalidOpenForReading();
    void testNullArguments();
    void testReadFromFileReusingPackets();
    void testReadFromFileWithNewPackets();
    void testOpenContainerWithoutClosing();
    void testCloseContainerWithoutOpening();
    void testAddStreams();
    void testReadASmallNumberOfSamplesFromFile();
    void testWriteHeader();;
  private:
    Helper* h;
    RefPointer<IContainer> container;
};


#endif // __CONTAINER_TEST_H__

