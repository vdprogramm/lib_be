package com.example.library_management_backend.controller;

import com.example.library_management_backend.constants.BookCopyStatusEnum;
import com.example.library_management_backend.dto.base.response.ApiResponse;
import com.example.library_management_backend.dto.base.response.BaseGetAllResponse;
import com.example.library_management_backend.dto.book_copy.request.BookCopyCreateManyRequest;
import com.example.library_management_backend.dto.book_copy.request.BookCopyCreationRequest;
import com.example.library_management_backend.dto.book_copy.request.BookCopyGetAllRequest;
import com.example.library_management_backend.dto.book_copy.request.BookCopyUpdateRequest;
import com.example.library_management_backend.dto.book_copy.response.BookCopyResponse;
import com.example.library_management_backend.entity.BookCopy;
import com.example.library_management_backend.mapper.BookCopyMapper;
import com.example.library_management_backend.service.BookCopyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-copies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookCopyController {
    BookCopyService bookCopyService;
    BookCopyMapper bookCopyMapper;

    @PostMapping("/Create")
    ApiResponse<BookCopyResponse> createBookCopy(@RequestBody BookCopyCreationRequest request) {
        BookCopy bookCopy = bookCopyService.createBookCopy(request);
        return ApiResponse.<BookCopyResponse>builder()
                .result(bookCopyMapper.toBookCopyResponse(bookCopy))
                .build();
    }

    @PostMapping("/CreateMany")
    ApiResponse<List<BookCopyResponse>> createManyBookCopies(@RequestBody BookCopyCreateManyRequest request) {
        return ApiResponse.<List<BookCopyResponse>>builder()
                .result(bookCopyService.createManyBookCopies(request))
                .build();
    }

    @GetMapping("/GetAll")
    ApiResponse<BaseGetAllResponse<BookCopyResponse>> getAllBookCopies(
            @RequestParam(value = "bookId", required = false) String bookId,
            @RequestParam(value = "bookTitle", required = false) String bookTitle,
            @RequestParam(value = "status", required = false) BookCopyStatusEnum status,
            @RequestParam(value = "skipCount", defaultValue = "0") int skipCount,
            @RequestParam(value = "maxResultCount", defaultValue = "10") int maxResultCount) {

        BookCopyGetAllRequest request = BookCopyGetAllRequest.builder()
                .bookId(bookId)
                .bookTitle(bookTitle)
                .status(status)
                .skipCount(skipCount)
                .maxResultCount(maxResultCount)
                .build();

        return ApiResponse.<BaseGetAllResponse<BookCopyResponse>>builder()
                .result(bookCopyService.getAllBookCopies(request))
                .build();
    }

    @GetMapping("/GetById")
    public ApiResponse<BookCopyResponse> getBookCopyById(@RequestParam String id) {
        return ApiResponse.<BookCopyResponse>builder()
                .result(bookCopyService.getBookCopyById(id))
                .build();
    }

    @PutMapping("/Update")
    ApiResponse<BookCopyResponse> updateBookCopy(@RequestBody BookCopyUpdateRequest request) {
        return ApiResponse.<BookCopyResponse>builder()
                .result(bookCopyService.updateBookCopy(request))
                .build();
    }

    @DeleteMapping("/Delete")
    ApiResponse<String> deleteBookCopy(@RequestParam String id) {
        bookCopyService.deleteBookCopy(id);
        return ApiResponse.<String>builder()
                .result("Book copy deleted successfully")
                .build();
    }
}

