package com.bifos.foslog.web;

import com.bifos.foslog.service.snackinthegarden.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SnackInTheGardenApiController {

    private final ContractService contractService;

//    @PostMapping("/api/v1/posts")
//    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
//        return postsService.save(requestDto);
//    }
//
//    @PutMapping("/api/v1/posts/{id}")
//    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
//        return postsService.update(id, requestDto);
//    }
//
//    @DeleteMapping("/api/v1/posts/{id}")
//    public Long delete(@PathVariable Long id) {
//        postsService.delete(id);
//        return id;
//    }
//
//    @GetMapping("/api/v1/posts/{id}")
//    public PostsResponseDto findById(@PathVariable Long id) {
//        return postsService.findById(id);
//    }

//    @PostMapping("/api/v1/customers")
//    public Long save(@RequestBody )
}