package com.example.demo;

import com.example.demo.event.ContentEvent;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.assertj.core.internal.Bytes;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	UserService userService;

	@Test
	public void userTest() {
		User user = new User();
		user.setId(4);
		user.setName("name4");
		user.setSex((byte)1);
		user.setAge(24);
		userService.addUser(user);

		User newUser = userService.getUserById(4);
		userService.getUserById(4);
		System.out.println(newUser.getName());
	}

	@Test
	public void simpleTest() throws Exception{

		List<String> list = mock(List.class);

		when(list.get(anyInt())).thenReturn("hello","world");

		String result = list.get(0)+list.get(1);

		verify(list,times(2)).get(anyInt());

		applicationContext.publishEvent(new ContentEvent("This is a test..."));

		assertThat(result).isEqualTo("helloworld");
		assertThat(1/2.0).isEqualTo(0.5);

		FunctionTest functionTest = new FunctionTest();
		System.out.println(functionTest.intToString(Integer::toBinaryString));
		System.out.println(functionTest.intToString(i->Integer.toString(i)));
		System.out.println(functionTest.intToString(this::intToString));
		System.out.println(functionTest.intToString(DemoApplicationTests::intToString1));

		StringBuilder stringBuilder = new StringBuilder("hello");
		System.out.println(stringBuilder.reverse());

		//测试type类型
		Method method = getClass().getMethod("methodIV", ArrayList.class,
				ArrayList.class, ArrayList.class, ArrayList.class, ArrayList.class);
		Type[] types = method.getGenericParameterTypes();
		ParameterizedType param1 = (ParameterizedType)types[4];
		Type[] types1 = param1.getActualTypeArguments();
		Type param1_type = param1.getRawType();
		System.out.println(param1_type.getTypeName());
		System.out.println(types1[0].getTypeName());
		//GenericArrayType gat = (GenericArrayType)types1[0];
		//Type arrElementType = gat.getGenericComponentType();
		Class<?> arr = (Class<?>)types1[0];
		assertThat(arr.isArray()).isTrue();
		assertThat(types1[0] instanceof Class).isTrue();
		System.out.println(arr.getComponentType().getTypeName());
	}

	public static <E> E methodIV(ArrayList<ArrayList> al1, ArrayList<E> al2, ArrayList<String> al3,
							  ArrayList<? extends Number> al4, ArrayList<String[]> al5) {
		return al2.get(0);
	}

	private String intToString(Integer i) {
		return i.toString() + " hello";
	}

	private static String intToString1(Integer i) {
		return i.toString() + " world";
	}

	private static class Point {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "x:" + x + " y:" + y;
		}
	}

}
