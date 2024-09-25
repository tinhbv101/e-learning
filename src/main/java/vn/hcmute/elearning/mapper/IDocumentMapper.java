package vn.hcmute.elearning.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import vn.hcmute.elearning.entity.Document;
import vn.hcmute.elearning.model.DocumentDto;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface IDocumentMapper {

    DocumentDto toDocumentDto(Document document);
    List<DocumentDto> toListDocumentDto(List<Document> documents);
}
