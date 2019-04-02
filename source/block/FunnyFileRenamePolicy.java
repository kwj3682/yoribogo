package kr.co.yoribogo.block;
import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FunnyFileRenamePolicy implements FileRenamePolicy{
	@Override
	public File rename(File f) {
		//사용자가 선택한 이름
		String parent = f.getParent();
		
		String name = f.getName();
		
		String ext = "";
		int index = name.lastIndexOf(".");
		if(index != -1) ext = name.substring(index);
		
		//사용자가 선택한 파일의 이름에서 확장자 부분은 유지하고 이름 부분만을 고유한 값으로 설정한다
//		return new File("c:/bit2019/aaa.txt");
		
		String uName = UUID.randomUUID().toString();
//		return new File(parent+"/"+uName + ext);
		return new File(parent, uName + ext);
	}
}
