package com.example.jpa.test;

//Junit Life Cycle

import org.junit.jupiter.api.*;

class InitTest {
    /**
     * @BeforeAll
     * - 모든 테스트 전에 단 한 번만 실행되는 메소드 (static 선언 필수)
     */    @BeforeAll
    static void beforeAll() {
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }
    /**
     * @AfterAll
     * - 모든 테스트 후에 단 한 번만 실행되는 메소드 (static 선언 필수)
     */
    @AfterAll
    static void afterAll() {
        System.out.println("## afterAll Annotation 호출 ##");
        System.out.println();
    }
    /**
     * @BeforeEach
     * - 각 테스트 전에 실행되는 메소드
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("## beforeEach Annotation 호출 ##");
        System.out.println();
    }
    /**
     * @AfterEach
     * - 각 테스트 후에 실행되는 메소드
     */
    @AfterEach
    void afterEach() {
        System.out.println("## afterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("## test1 시작 ##");
        System.out.println();
    }
    /**
     * @DisplayName()
     * - 각 메소드의 테스트명 설정
     */
    @Test
    @DisplayName("Test Case 2!!!")
    void test2() {
        System.out.println("## test2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled
        // Disabled Annotation : 테스트를 실행하지 않게 설정하는 어노테이션
    void test3() {
        System.out.println("## test3 시작 ##");
        System.out.println();
    }
    /**
     * @DisplayName()
     * - 각 메소드의 테스트명 설정
     */
    @Test
    @DisplayName("DisplayName 적용")
    void displayNameTest() {
        System.out.println("displayNameTest");
    }
}