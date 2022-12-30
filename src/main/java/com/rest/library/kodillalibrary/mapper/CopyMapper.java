package com.rest.library.kodillalibrary.mapper;

import com.rest.library.kodillalibrary.domain.Copy;
import com.rest.library.kodillalibrary.dto.CopyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyMapper {

    public CopyDto mapCopyToCopyDto(List<Copy> copies) {
        return new CopyDto (
                copies.get(0).getBook().getAuthor(),
                copies.get(0).getBook().getTitle(),
                copies.size()
        );
    }
}
