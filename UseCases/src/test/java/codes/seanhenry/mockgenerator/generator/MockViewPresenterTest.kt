package codes.seanhenry.mockgenerator.generator

import codes.seanhenry.mockgenerator.generator.templates.*
import junit.framework.TestCase

class MockViewPresenterTest : TestCase() {

  lateinit var generator: MockViewPresenter
  lateinit var view: MustacheMockView

  override fun setUp() {
    super.setUp()
    view = MustacheMockView()
    generator = MockViewPresenter(view)
  }

  fun testShouldReturnEmptyString_whenNothingToMock() {
    assert(generator.generate().isEmpty())
  }

  fun testSimpleProtocol() {
    runTest(SimpleProtocolTest())
  }

  fun testMethodParameter() {
    runTest(MethodParameterTest())
  }

  fun testDefaultValues() {
    runTest(DefaultValuesTest())
  }

  fun testOverloadProtocol() {
    runTest(OverloadProtocolTest())
  }

  fun testPropertyProtocol() {
    runTest(PropertyProtocolTest())
  }

  fun testReturnProtocol() {
    runTest(ReturnProtocolTest())
  }

  fun testScopeProtocol() {
    runTest(ScopeProtocolTest())
  }

//  fun testClosureProtocol() {
//    runTest(ClosureProtocolTest())
//  }
//
//  fun testAnnotations() {
//    runTest(ParameterAnnotationsTest())
//  }
//
//  fun testTypeAlias() {
//    runTest(TypealiasProtocolTest())
//  }
//
//  fun testKeywords() {
//    runTest(KeywordsProtocolTest())
//  }
//
//  fun testSimpleClass() {
//    runTest(SimpleClassTest())
//  }
//
//  fun testInitialiserWithArguments() {
//    runTest(ArgumentsInitialiserTest())
//  }
//
//  fun testOptionalInitialser() {
//    runTest(FailableInitialserTest())
//  }
//
//  fun testNoArgumentOptionalInitialiser() {
//    runTest(NoArgumentFailableInitialiserTest())
//  }
//
//  fun testNoProtocolInitialiser() {
//    runTest(ProtocoInitialiserTest())
//  }
//
//  fun testFiltersToSimplestInitialiser() {
//    runTest(SimplestClassInitialiserTest())
//  }
//
//  fun testOpenInitialiserShouldSetInitToPublic() {
//    runTest(OpenInitialiserTest())
//  }
//
//  fun testStubsThrowableMethods() {
//    runTest(ThrowingTest())
//  }
//
//  fun testStubsThrowableInitialisers() {
//    runTest(ThrowingInitialiserTest())
//  }
//
//  fun testGenericParameters() {
//    runTest(GenericParametersTest())
//  }
//
//  fun testGenericReturnValue() {
//    runTest(GenericReturnValueTest())
//  }

  private fun runTest(template: MockGeneratorTestTemplate) {
    template.build(generator)
    generator.generate()
    assertEquals(template.getExpected(), view.rendered)
  }
}
