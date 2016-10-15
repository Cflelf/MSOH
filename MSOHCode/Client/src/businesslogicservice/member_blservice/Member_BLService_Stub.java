package businesslogicservice.member_blservice;

import java.util.Date;

import util.MemberType;
import util.ResultMessage;

/**
 * @author Pxr created:2016.10.14 latest modify:2016.10.15
 */
public class Member_BLService_Stub implements Member_BLService {
	// ��Ա�ȼ���Ϣ
	MemberLevelVO memberLevelVO;
	// �û���Ϣ
	CustomerVO customerVO;
	// ��Ա��Ϣ
	MemberVO memberVO;

	/**
	 * ��Աע��
	 */
	@Override
	public ResultMessage signUp(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// ���û�����ֵ�������ý��ޣ������ע���Ա����ѡ���Ա����
		if (customerVO.credit >= memberLevelVO.creditBoundries[1]) {
			// ���û�δѡ���Ա���ͣ���������Ϣ�հ�
			if (memberLevelVO.memberType == null) {
				return ResultMessage.Blank;
			}
			// ���û�ѡ���Ϊ��ͨ��Ա
			else if (memberLevelVO.memberType.equals("NORMAL")) {
				// ���û�δ��д���գ���������Ϣ�հ�
				if (memberLevelVO.birthday == null) {
					return ResultMessage.Blank;
				}
				// ���û�������дΪ12.25���򷵻�ע��ɹ�
				else if (memberVO.birthday.equals(12.25)) {
					return ResultMessage.SignUp_Success;
				}
			}
			// ���û�ѡ���Ϊ��ҵ��Ա
			else if (memberLevelVO.MemberType.equals("ENTREPRENEUR")) {
				// ���û�δ��д��ҵ������������Ϣ�հ�
				if (memberVO.companyName == null) {
					return ResultMessage.Blank;
				}
				// ���û���ҵ����дΪ����Ƥ�ﳧ���򷵻�ע��ɹ�
				else if (memberVO.companyName.equals("����Ƥ�ﳧ")) {
					return ResultMessage.SignUp_Success;
				}
			}
		} else {
			return ResultMessage.Credit_Not_Enough;
		}
	}

	/**
	 * ��Ա����
	 */
	@Override
	public void Upgrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// ���û�Ϊ��Աһ��������ֵ������Ա2����������
		if (memberVO.level == 1 && customerVO.credit >= memberLevelVO.creditBoundries[2]) {
			memberVO.level++;
		}
	}

	/**
	 * ��Ա����
	 */
	@Override
	public void Degrade(MemberLevelVO memberLevelVO, MemberVO memberVO, CustomerVO customerVO) {
		// ���û�Ϊ����������ֵ���㣬�򽵼�
		if (memberVO.level == 2 && customerVO.credit <= memberLevelVO.creditBoundries[2]
				&& customerVO.credit >= memberLevelVO.creditBoundries[1]) {
			customerVO.level--;
		}
	}

	/**
	 * ��ȡ��Ա
	 */
	@Override
	public MemberVO getSingle(String ID) {
		if (ID.equals("12358")) {
			return memberVO;
		} else if (ID.equals("00000")) {
			return null;
		}
	}

}
