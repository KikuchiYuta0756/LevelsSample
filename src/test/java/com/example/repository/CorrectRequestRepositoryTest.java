package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.domainUser.model.CorrectRequestEntity;

@ExtendWith(MockitoExtension.class)
class CorrectRequestRepositoryTest {

		@Mock
		private CorrectRequestRepository correctRequestRepository;

		@Mock
		private CorrectRequestEntity correctRequestEntity;

		@Test
		public void testInsertOne() {
		when(correctRequestRepository.insertOne(correctRequestEntity)).thenReturn(1);
		int result = correctRequestRepository.insertOne(correctRequestEntity);
		
		assertEquals(1, result);
		verify(correctRequestRepository, times(1)).insertOne(correctRequestEntity);
		}

		@Test
		public void testFindMany() {
		List<CorrectRequestEntity> expectedList = Arrays.asList(correctRequestEntity);
		when(correctRequestRepository.findMany(correctRequestEntity)).thenReturn(expectedList);

		List<CorrectRequestEntity> resultList = correctRequestRepository.findMany(correctRequestEntity);
		
		assertEquals(expectedList, resultList);
		verify(correctRequestRepository, times(1)).findMany(correctRequestEntity);
		}

		@Test
		public void testFindOne() {
		int correctRequestId = 1;
		when(correctRequestRepository.findOne(correctRequestId)).thenReturn(correctRequestEntity);
		CorrectRequestEntity result = correctRequestRepository.findOne(correctRequestId);
		
		assertEquals(correctRequestEntity, result);
		verify(correctRequestRepository, times(1)).findOne(correctRequestId);
		}

		@Test
		public void testUpdateStaApproval() {
        // メソッド呼び出しをテスト
        int correctRequestId = 123; // 任意のIDを設定
        correctRequestRepository.updateStaApproval(correctRequestId);

        // メソッドが正しく呼び出されたかどうかを検証
        verify(correctRequestRepository).updateStaApproval(correctRequestId);
        }

		@Test
		public void testUpdateStaRemand() {
	    // メソッド呼び出しをテスト
	    int correctRequestId = 123; // 任意のIDを設定
	    correctRequestRepository.updateStaRemand(correctRequestId);

	    // メソッドが正しく呼び出されたかどうかを検証
	    verify(correctRequestRepository,times(1)).updateStaRemand(correctRequestId);
		}

		@Test
		public void testUserFindMany() {
		String correctLoginId = "testUser";
		List<CorrectRequestEntity> expectedList = Arrays.asList(correctRequestEntity);
		when(correctRequestRepository.userFindMany(correctLoginId)).thenReturn(expectedList);
		List<CorrectRequestEntity> resultList = correctRequestRepository.userFindMany(correctLoginId);
		
		assertEquals(expectedList, resultList);
		verify(correctRequestRepository, times(1)).userFindMany(correctLoginId);
		}
}
