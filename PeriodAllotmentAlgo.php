<?php
/*
class id: year_branch_section
eg: 3_CSE_2
*/

/*
lab id: building_labno.
eg: BB_6
*/

/*
day 1: Monday
day 2: Tuesday
day 3: Wednesday
day 4: Thursday
day 5: Friday
day 6: Saturday
*/

/*
slot 1: 0830 to 0920
slot 2: 0920 to 1010
slot 3: 1010 to 1100
slot 4: 1100 to 1150
slot 5: 1150 to 1240
slot 6: 1240 to 1330
slot 7: 1330 to 1420
slot 8: 1420 to 1510
slot 9: 1510 to 1600
*/

/*
classtype 1: class
classtype 2: lab
*/
$dbhost = 'localhost';
$dbname = 'timetable';
$dbuser = 'root';
$dbpass = '';

$conn = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname);

if(isset($_POST['timetable_type'])) {
	$timetable_type = $_POST['timetable_type'];
	switch ($timetable_type) {
		case 0:
			/*class*/
			updateClass($conn, $class_id, $day, $slot_no, $faculty_id, $subject_id);
			break;
		
		case 1:
			/*faculty*/
			updateFaculty($conn, $faculty_id, $day, $slot_no, $class_type, $class_id, $subject_id);
			break;

		case 2:
			/*lab*/
			updateLab($conn, $lab_id, $day, $slot_no, $faculty_id1, $faculty_id2, $class_id, $subject_id);
			break;
	}
}

function updateClass($conn, $class_id, $day, $slot_no, $faculty_id, $subject_id) {
	$class = checkClassFree($class_id, $day, $slot_no);
	$faculty = checkFacultyFree($faculty_id, $day, $slot_no);
	if($class>=1) {
		/*Already a class in the slot*/
		echo "<script>alert('Already a class in the slot')</script>";
	}
	else if($faculty>=1) {
		/*Faculty is busy in the slot*/
		echo "<script>alert('Faculty $faculty is busy in the slot')</script>";	
	}
	else {
		$classtype = 1;
		$up_class = mysql_query($conn, "INSERT INTO class (class_id, day, slot_no, faculty_id, subject_id) VALUES ('$class_id', '$day', '$slot_no', '$faculty_id', '$subject_id') ");
		$up_faculty = mysql_query($conn, "INSERT INTO faculty (faculty_id, day, slot_no, class_id, subject_id) VALUES ('$class_id', '$day', '$slot_no', '$faculty_id', '$subject_id')");	
		if($up_class == 1 && $up_faculty == 1) {
			echo "<script>alert('Updated schedule for class')</script>";		
		}
	}
}

function updateFaculty($conn, $faculty_id, $day, $slot_no, $class_type, $class_id, $subject_id) {
	$faculty = checkFacultyFree($faculty_id2, $day, $slot_no);
	if($class_type == 1)
		$type = checkClassFree($class_id, $day, $slot_no);
	else if($class_type == 2)
		$type = checkLabFree($class_id, $day, $slot_no);
	
	if($faculty1 >= 1)
		echo "<script>alert('Faculty $faculty1 is busy in the slot')</script>";
	else if($type>=1 && $class_type==1)
		echo "<script>alert('Some periods are already assigned for the class')</script>";
	else if($type>=2 && $class_type==2)
		echo "<script>alert('Lab is not free, choose another lab')</script>";
	else {
		$up_faculty = mysql_query($conn, "INSERT INTO faculty (faculty_id, day, slot_no, class_id, subject_id) VALUES ('$class_id', '$day', '$slot_no', '$faculty_id', '$subject_id')");
		if($class_type == 1)
			$up_class = mysql_query($conn, "INSERT INTO class (class_id, day, slot_no, faculty_id, subject_id) VALUES ('$class_id', '$day', '$slot_no', '$faculty_id', '$subject_id') ");
	}
}

function updateLab($conn, $lab_id, $day, $slot_no, $faculty_id1, $faculty_id2, $class_id, $subject_id) {
	$faculty1 = checkFacultyFree($faculty_id1, $day, $slot_no);
	$faculty2 = checkFacultyFree($faculty_id2, $day, $slot_no);
}

function checkClassFree($class_id, $day, $slot_no) {
	$class = mysql_query($conn, "SELECT * FROM class WHERE class_id='$class_id' AND day='$day' AND slot_no='$slot_no'");
	return mysql_num_rows($class);
}

function checkFacultyFree($faculty_id, $day, $slot_no) {
	$faculty = mysql_query($conn, "SELECT * FROM faculty WHERE faculty_id='$faculty_id' AND day='$day' AND slot_no='$slot_no'");
	return mysql_num_rows($faculty);
}

function checkLabFree($lab_id, $day, $slot_no) {
	$lab = mysql_query($conn, "SELECT * FROM lab WHERE lab_id='$faculty_id' AND day='$day' AND slot_no='$slot_no'");
	return mysql_num_rows($lab);
}
?>