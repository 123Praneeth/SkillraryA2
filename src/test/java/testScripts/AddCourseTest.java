

	
	package testScripts;

	import java.util.Map;

	import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities.IConstantPath;

	public class AddCourseTest extends BaseClass {
		@Test
		public void addCourseTest() {
			SoftAssert soft = new SoftAssert();
			home.clickCoursesTab();
			home.clickCourseListLink();
			soft.assertTrue(courseList.getPageHeader().contains("Course List"));
			courseList.clickNewButton();
			soft.assertEquals(addCourse.getPageHeader(), "Add New Course");
			//This test verifes if user is able to add course
			Map<String, String> map = excel.readFromExcel("Add Course");
			addCourse.setName(map.get("Name"));
			addCourse.selectcategory(web, map.get("Category"));
			addCourse.setPrice(map.get("Prize"));
			addCourse.uploadPhoto(map.get("Photo"));
			addCourse.setDescription(map.get("Description"), web);
			addCourse.clickSave();

			soft.assertEquals(courseList.getSuccessMessage(), "Success!");
			courseList.deleteCourse(web, map.get("Name"));
			soft.assertEquals(courseList.getSuccessMessage(), "Success!");
			
			if(courseList.getSuccessMessage().equals("Success!"))
				excel.updateTestStatus("Add Course", "Pass", IConstantPath.EXCEL_PATH);
			else
				excel.updateTestStatus("Add Course", "Fail", IConstantPath.EXCEL_PATH);
			
			soft.assertAll();
				
		}

	}



